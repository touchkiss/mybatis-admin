package com.touchkiss.mybatis.admin.controller;

import com.touchkiss.mybatis.admin.exception.NoSuchTableConfigException;
import com.touchkiss.mybatis.admin.bean.RegisterInfo;
import com.touchkiss.mybatis.admin.config.AdminConfig;
import com.touchkiss.mybatis.admin.exception.ErrorCompareValueException;
import com.touchkiss.mybatis.admin.exception.ErrorParseSelectorException;
import com.touchkiss.mybatis.sqlbuild.keyword.Sort;
import com.touchkiss.mybatis.sqlbuild.query.QColumn;
import com.touchkiss.mybatis.sqlbuild.query.QTable;
import com.touchkiss.mybatis.sqlbuild.selector.Selector;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 适配jQuery DataTables
 *
 * @Author Touchkiss
 * @create: 2019-06-25 14:28
 */
public abstract class JQueryDataTableController extends BaseController {

    /**
     * 获取selector
     *
     * @param name
     * @param strict
     * @param <T>
     * @return
     * @throws UnsupportedEncodingException
     * @throws ErrorCompareValueException
     * @throws ErrorParseSelectorException
     * @throws NoSuchTableConfigException
     */
    @Override
    public <T> Selector getSelector(String name, Boolean strict) throws UnsupportedEncodingException, ErrorCompareValueException, ErrorParseSelectorException, NoSuchTableConfigException {

        if (!AdminConfig.registerInfoMap.containsKey(name)) {
            throw new NoSuchTableConfigException("无此表配置信息");
        }
        Map<String, String[]> requestParameterMap = request.getParameterMap();
        RegisterInfo registerInfo = AdminConfig.registerInfoMap.get(name);

        QTable qTable = new QTable(registerInfo.getName());
        Selector<T> selector = new Selector<T>();
        selector.setTable(qTable);
        for (int i = 0; ; i++) {
            if (requestParameterMap.containsKey("columns[" + i + "][data]")) {
                String[] values = requestParameterMap.get("columns[" + i + "][data]");
                if (values != null && values.length > 0 && StringUtils.isNotEmpty(values[0])) {
                    if (strict) {
                        if (registerInfo.getBeanInfo().getBeanPropertyInfoMap().containsKey(values[0])) {
                            selector.addField(new QColumn(qTable, getColumnName(values[0])));
                        } else {
                            throw new ErrorParseSelectorException("列：" + values[0] + "不存在");
                        }
                    } else {
                        selector.addField(new QColumn(qTable, getColumnName(values[0])));
                    }
                }
            } else {
                break;
            }
        }
        for (int i = 0; ; i++) {
            if (requestParameterMap.containsKey("order[" + i + "][column]") && requestParameterMap.containsKey("order[" + i + "][dir]")) {
                String[] orderValue = requestParameterMap.get("order[" + i + "][column]");
                String[] orderDir = requestParameterMap.get("order[" + i + "][dir]");
                String order = orderValue[0];
                if (orderValue != null && orderValue.length > 0 && StringUtils.isNotEmpty(order) && orderDir != null && orderDir.length > 0) {
                    String[] values = requestParameterMap.get("columns[" + order + "][data]");
                    if (values == null || values.length < 1 || StringUtils.isEmpty(values[0])) {
                        continue;
                    }
                    String columnName = getColumnName(values[0]);
                    if (StringUtils.isEmpty(columnName)) {
                        continue;
                    }
                    if (strict) {
                        if (registerInfo.getBeanInfo().getBeanPropertyInfoMap().get(values[0]).isOrder()) {
                            selector.order(new QColumn(qTable, columnName), Sort.valueOf(orderDir[0].toUpperCase()));
                        } else {
                            throw new ErrorParseSelectorException("列：" + order + "不允许排序");
                        }
                    } else {
                        selector.order(new QColumn(qTable, columnName), Sort.valueOf(orderDir[0].toUpperCase()));
                    }
                }
            } else {
                break;
            }
        }
        buildWhereConditioins(selector, requestParameterMap, name, strict);
        return selector;
    }
}
