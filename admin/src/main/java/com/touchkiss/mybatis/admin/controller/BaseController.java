package com.touchkiss.mybatis.admin.controller;

import com.google.gson.internal.Primitives;
import com.touchkiss.mybatis.admin.bean.Menu;
import com.touchkiss.mybatis.admin.bean.RegisterInfo;
import com.touchkiss.mybatis.admin.exception.ErrorCompareValueException;
import com.touchkiss.mybatis.admin.exception.ErrorParseSelectorException;
import com.touchkiss.mybatis.admin.exception.NoSuchTableConfigException;
import com.touchkiss.mybatis.admin.config.AdminConfig;
import com.touchkiss.mybatis.sqlbuild.condition.ManyCondition;
import com.touchkiss.mybatis.sqlbuild.condition.SingleCondition;
import com.touchkiss.mybatis.sqlbuild.keyword.CompareOperator;
import com.touchkiss.mybatis.sqlbuild.keyword.CompareSuffix;
import com.touchkiss.mybatis.sqlbuild.keyword.SqlLogic;
import com.touchkiss.mybatis.sqlbuild.query.QColumn;
import com.touchkiss.mybatis.sqlbuild.query.QTable;
import com.touchkiss.mybatis.sqlbuild.selector.Selector;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * @Author Touchkiss
 * @create: 2019-06-25 08:38
 */
public abstract class BaseController {
    @Autowired
    protected HttpServletRequest request;
    @Resource
    protected HttpServletResponse response;

    final static String ENCODING = "utf-8";
    final static DateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

    abstract <T> Selector getSelector(String tableName, Boolean strict) throws UnsupportedEncodingException, ErrorCompareValueException, ErrorParseSelectorException, NoSuchTableConfigException;

    final static Pattern leftBracket = Pattern.compile("\\(");
    final static Pattern rightBracket = Pattern.compile("\\)");

    /**
     * 获取菜单
     *
     * @param httpSession
     * @return
     */
    public Menu getMenu(HttpSession httpSession) {
        Menu menu = (Menu) httpSession.getAttribute("menu");
        if (menu == null) {
            menu = new Menu();
            for (Map.Entry<String, RegisterInfo> stringRegisterInfoEntry : AdminConfig.registerInfoMap.entrySet()) {
                boolean existsThisMenuGroup = false;
                RegisterInfo registerInfo = stringRegisterInfoEntry.getValue();
                String groupShowName = registerInfo.getGroupShowName();
                String menuShowName = registerInfo.getShowName();
                String name = stringRegisterInfoEntry.getKey();
                String group = registerInfo.getGroup();
                if (menu.getMenuGroups() == null) {
                    menu.setMenuGroups(new ArrayList<>());
                }
                for (Menu.MenuGroup menuGroup : menu.getMenuGroups()) {
                    if (menuGroup.getShowName().equals(groupShowName)) {
                        Menu.MenuGroup.MenuItem menuItem = new Menu.MenuGroup.MenuItem();
                        menuItem.setName(name);
                        menuItem.setShowName(menuShowName);
                        menuItem.setIconClass(registerInfo.getNameIconClass());
                        menuGroup.getMenuItems().add(menuItem);
                        existsThisMenuGroup = true;
                        break;
                    }
                }
                if (!existsThisMenuGroup) {
                    Menu.MenuGroup menuGroup = new Menu.MenuGroup();
                    menuGroup.setShowName(groupShowName);
                    menuGroup.setName(group);
                    List<Menu.MenuGroup.MenuItem> menuItemList = new ArrayList<>();
                    Menu.MenuGroup.MenuItem menuItem = new Menu.MenuGroup.MenuItem();
                    menuItem.setShowName(menuShowName);
                    menuItem.setName(name);
                    menuItem.setIconClass(registerInfo.getGroupIconClass());
                    menuItemList.add(menuItem);
                    menuGroup.setMenuItems(menuItemList);
                    menu.getMenuGroups().add(menuGroup);
                }
            }
            httpSession.setAttribute("menu", menu);
        }
        return menu;
    }

    /**
     * 构建where查询条件
     *
     * @param selector
     * @param requestParameterMap
     * @param tableName
     * @param strict
     * @throws UnsupportedEncodingException
     * @throws ErrorCompareValueException
     * @throws ErrorParseSelectorException
     */
    void buildWhereConditioins(Selector selector, Map<String, String[]> requestParameterMap, String tableName, Boolean strict) throws UnsupportedEncodingException, ErrorCompareValueException, ErrorParseSelectorException {
        for (Map.Entry<String, String[]> stringEntry : requestParameterMap.entrySet()) {
            String key = stringEntry.getKey();
            String[] values = stringEntry.getValue();
            if (values == null || values.length < 1) {
                continue;
            }
            String value = values[0];
            if (StringUtils.isEmpty(value)) {
                continue;
            }
            RegisterInfo registerInfo = AdminConfig.registerInfoMap.get(tableName);
            QTable qTable = new QTable(registerInfo.getName());

            if (key.startsWith("_q_")) {
//                查询条件，单一条件
                String afterType;
                if ("_q_".equals(key)) {
                    afterType = "cn";
                } else {
                    afterType = key.substring(3);
                }
                for (CompareSuffix compareSuffix : CompareSuffix.values()) {
                    String suffix = compareSuffix.name();
                    if (suffix.equals(afterType)) {
                        CompareOperator compareOperator = CompareOperator.valueOf(compareSuffix.toString());
                        ManyCondition manyCondition = new ManyCondition();
                        for (String columnName : registerInfo.getBeanInfo().getSearchFields()) {
                            ManyCondition mc = getManyCondition(value, compareOperator, new QColumn(qTable, getColumnName(columnName)));
                            manyCondition.add(mc);
                        }
                        manyCondition.setSeparator(SqlLogic.OR);
                        selector.where(manyCondition);
                        break;
                    }
                }

            } else if (key.startsWith("_p_")) {
//                过滤条件，可复杂条件
                String afterType = key.substring(3);
                for (CompareSuffix compareSuffix : CompareSuffix.values()) {
                    String suffix = compareSuffix.name();
                    if (afterType.indexOf(suffix) > 0 && afterType.endsWith(suffix)) {
                        String columnName = afterType.substring(0, afterType.lastIndexOf(suffix) - 1);
                        CompareOperator compareOperator = CompareOperator.valueOf(compareSuffix.toString());
                        if (strict) {
                            if (registerInfo.getBeanInfo().getFilterFieldSet().contains(columnName)) {
                                selector.where(getManyCondition(value, compareOperator, new QColumn(qTable, getColumnName(columnName))));
                            } else {
                                throw new ErrorParseSelectorException("列：" + columnName + "不支持过滤");
                            }
                        } else {
                            selector.where(getManyCondition(value, compareOperator, new QColumn(qTable, getColumnName(columnName))));
                        }
                        break;
                    }
                }
            }
        }
    }

    /**
     * 取得列名，mybatis列中包含下划线时，xml自动生成工具生成符合驼峰命名规则的变量名
     *
     * @param col
     * @return
     */
    String getColumnName(String col) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(col.charAt(0));
        if (col.length() > 1) {
            for (int i = 1; i < col.length(); i++) {
                char c = col.charAt(i);
                if (c >= 'A' && c <= 'Z') {
                    stringBuilder.append('_');
                    stringBuilder.append((c + "").toLowerCase());
                } else {
                    stringBuilder.append(c);
                }
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 获取where条件
     *
     * @param strings
     * @param compareOperator
     * @param field
     * @return
     * @throws ErrorCompareValueException
     * @throws UnsupportedEncodingException
     */
    private ManyCondition getManyCondition(List<String> strings, CompareOperator compareOperator, QColumn<?, ?> field) throws ErrorCompareValueException, UnsupportedEncodingException {
        Stack<ManyCondition> conditionStack = new Stack<>();
        for (String item : strings) {
            if (item.equals("^") || item.equals("+")) {
                ManyCondition manyCondition = new ManyCondition();
                manyCondition.add(conditionStack.pop());
                manyCondition.add(conditionStack.pop());
                manyCondition.setSeparator("^".equals(item) ? SqlLogic.OR : SqlLogic.AND);
                conditionStack.push(manyCondition);
            } else {
                ManyCondition manyCondition = new ManyCondition();
                String decode = URLDecoder.decode(item, ENCODING);
                decode = decode.replaceAll("%2b", "+");
                decode = decode.replaceAll("%5e", "^");
                manyCondition.add(new SingleCondition(field, compareOperator, (compareOperator == CompareOperator.LIKE || compareOperator == CompareOperator.NOT_LIKE) ? ("%" + decode + "%") : decode));
                conditionStack.push(manyCondition);
            }
        }
        if (conditionStack.size() == 1) {
            return conditionStack.pop();
        } else {
            throw new ErrorCompareValueException("搜索表达式解析失败！");
        }
    }

    /**
     * 获取where条件
     *
     * @param source
     * @param compareOperator
     * @param field
     * @return
     * @throws ErrorCompareValueException
     * @throws UnsupportedEncodingException
     */
    private ManyCondition getManyCondition(String source, CompareOperator compareOperator, QColumn<?, ?> field) throws ErrorCompareValueException, UnsupportedEncodingException {
        List<String> strings = transferToBehind(source);
        return getManyCondition(strings, compareOperator, field);
    }

    /**
     * 获取where条件
     *
     * @param source
     * @return
     * @throws ErrorCompareValueException
     */
    private List<String> transferToBehind(String source) throws ErrorCompareValueException {
        char[] chars = source.toCharArray();
        Stack<Character> operatorStack = new Stack<>();
        List<String> behindList = new ArrayList<>();
        int startIndex = 0;
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            switch (chars[i]) {
                case '(':
                    if (i > 0 && chars[i - 1] != '(' && chars[i - 1] != '+' && chars[i - 1] != '^') {
                        throw new ErrorCompareValueException("错误的搜索表达式，连续的‘)’和‘(’");
                    }
                    if (i == length - 1) {
                        throw new ErrorCompareValueException("错误的搜索表达式，以‘(’结尾");
                    }
                    operatorStack.push(chars[i]);
                    break;
                case ')':
                    if (i < 1) {
                        throw new ErrorCompareValueException("错误的搜索表达式，请勿以‘)’开始");
                    } else if (chars[i - 1] == '^' || chars[i - 1] == '+') {
                        throw new ErrorCompareValueException("错误的搜索表达式，连续的‘)’和‘^’或‘+’");
                    }
                    if (operatorStack.size() < 1) {
                        throw new ErrorCompareValueException("错误的搜索表达式，错误的‘)’");
                    }
                    boolean hasLeftBracket = false;
                    while (operatorStack.size() > 0) {
                        Character pop = operatorStack.pop();
                        if (pop.equals('(')) {
                            hasLeftBracket = true;
                            break;
                        } else {
                            behindList.add(String.valueOf(pop));
                        }
                    }
                    if (!hasLeftBracket) {
                        throw new ErrorCompareValueException("错误的搜索表达式，错误的‘)’");
                    }
                    break;
                case '+':
                case '^':
                    if (i == 0) {
                        throw new ErrorCompareValueException("错误的搜索表达式，请勿以‘-’或‘+’开始");
                    }
                    if (i > 0 && (chars[i - 1] == '^' || chars[i - 1] == '+' || chars[i - 1] == '(')) {
                        throw new ErrorCompareValueException("错误的搜索表达式，连续的‘^’和‘+’");
                    }
                    if (i == length - 1) {
                        throw new ErrorCompareValueException("错误的搜索表达式，以‘^’或‘+’结尾");
                    }
                    while (operatorStack.size() > 0) {
                        Character pop = operatorStack.pop();
                        if (pop.equals('+')&&chars[i]=='^') {
                            behindList.add(String.valueOf(pop));
                        } else {
                            operatorStack.push(pop);
                            break;
                        }
                    }
                    operatorStack.push(chars[i]);
                    break;
                default:
                    startIndex = i;
                    for (; i < length; ) {
                        if (i < length - 1) {
                            ++i;
                            if (chars[i] == '(' || chars[i] == ')' || chars[i] == '+' || chars[i] == '^') {
                                behindList.add(source.substring(startIndex, i));
                                --i;
                                break;
                            }
                        } else {
                            behindList.add(source.substring(startIndex, i + 1));
                            break;
                        }
                    }
                    break;
            }
        }
        int size = operatorStack.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                String pop = String.valueOf(operatorStack.pop());
                if (pop.equals("(")) {
                    throw new ErrorCompareValueException("错误的搜索表达式，错误的‘(’");
                }
                behindList.add(pop);
            }
        }
        if (CollectionUtils.isEmpty(behindList)) {
            throw new ErrorCompareValueException("空表达式");
        }
        return behindList;
    }

    /**
     * 根据前台传回的数据赋值给对象中的字段
     *
     * @param c
     * @param setStringNull
     * @param <T>
     * @return
     */
    public <T> T getParameter(Class<T> c, boolean setStringNull) {
        Object obj = null;
        String fieldName = "";
        try {
            obj = c.newInstance();

            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                try {
                    fieldName = field.getName();
                    field.setAccessible(true);
                    String fieldInRequest = request.getParameter(fieldName);

//            得到成员变量的类型的类类型
                    Class fieldType = field.getType();
//            得到成员变量类型的名字
                    String typeName = fieldType.getName();
                    switch (typeName) {
                        case "java.lang.Integer":
                            if (StringUtils.isNotBlank(fieldInRequest)) {
                                field.set(obj, Integer.valueOf(fieldInRequest));
                            }
                            break;
                        case "java.lang.String":
                            if (StringUtils.isNotBlank(fieldInRequest)) {
                                field.set(obj, fieldInRequest);
                            } else if (setStringNull) {
                                field.set(obj, "");
                            }
                            break;
                        case "java.lang.Long":
                            if (StringUtils.isNotBlank(fieldInRequest)) {
                                field.set(obj, Long.valueOf(fieldInRequest));
                            }
                            break;
                        case "java.lang.Double":
                            if (StringUtils.isNotBlank(fieldInRequest)) {
                                field.set(obj, Double.valueOf(fieldInRequest));
                            }
                            break;
                        case "java.lang.Boolean":
                            if (StringUtils.isNotBlank(fieldInRequest)) {
                                field.set(obj, Boolean.valueOf(fieldInRequest));
                            }
                            break;
                        case "java.lang.Float":
                            if (StringUtils.isNotBlank(fieldInRequest)) {
                                field.set(obj, Float.valueOf(fieldInRequest));
                            }
                            break;
                        case "java.lang.Short":
                            if (StringUtils.isNotBlank(fieldInRequest)) {
                                field.set(obj, Short.valueOf(fieldInRequest));
                            }
                            break;
                        case "java.lang.Byte":
                            if (StringUtils.isNotBlank(fieldInRequest)) {
                                field.set(obj, Byte.valueOf(fieldInRequest));
                            }
                            break;
                        case "java.util.Date":
                            if (StringUtils.isNotBlank(fieldInRequest)) {
                                field.set(obj, df.parse(fieldInRequest));
                            }
                            break;
                        default:
                            break;
                    }
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Primitives.wrap(c).cast(obj);
    }

    public Integer getIntegerParameter(String parameterName) {
        String paStr = request.getParameter(parameterName);
        if (StringUtils.isNotBlank(paStr)) {
            try {
                return Integer.parseInt(paStr);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public Long getLongParameter(String parameterName) {
        String paStr = request.getParameter(parameterName);
        if (StringUtils.isNotBlank(paStr)) {
            try {
                return Long.parseLong(paStr);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static class ProcessResult {
        public final static int PROCESS_SUCCEEE = 1;
        public final static int PROCESS_FAILED = 0;

        public Integer status;
        public String message;

        public ProcessResult() {
        }

        public ProcessResult(Integer status, String message) {
            this.status = status;
            this.message = message;
        }

        public static ProcessResult processSuccess(String message) {
            ProcessResult processResult = new ProcessResult();
            processResult.status = PROCESS_SUCCEEE;
            processResult.message=message;
            return processResult;
        }

        public static ProcessResult processSuccess() {
            ProcessResult processResult = new ProcessResult();
            processResult.status = PROCESS_SUCCEEE;
            return processResult;
        }

        public static ProcessResult processFailed(String message) {
            ProcessResult processResult = new ProcessResult();
            processResult.status = PROCESS_FAILED;
            processResult.message = message;
            return processResult;
        }
    }
}
