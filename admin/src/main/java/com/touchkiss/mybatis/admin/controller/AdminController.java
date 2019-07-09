package com.touchkiss.mybatis.admin.controller;

import com.github.pagehelper.Page;
import com.touchkiss.mybatis.admin.bean.BeanPropertyInfo;
import com.touchkiss.mybatis.admin.bean.ForeignKeyInfo;
import com.touchkiss.mybatis.admin.bean.PageUtil;
import com.touchkiss.mybatis.admin.bean.RegisterInfo;
import com.touchkiss.mybatis.admin.config.AdminConfig;
import com.touchkiss.mybatis.admin.exception.ErrorCompareValueException;
import com.touchkiss.mybatis.admin.exception.ErrorParseSelectorException;
import com.touchkiss.mybatis.admin.exception.NoSuchTableConfigException;
import com.touchkiss.mybatis.admin.utils.SpringUtil;
import com.touchkiss.mybatis.sqlbuild.selector.Selector;
import com.touchkiss.mybatis.sqlbuild.service.BaseService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Touchkiss
 * @create: 2019-06-20 15:49
 */
@RestController
@RequestMapping("admin")
public class AdminController extends JQueryDataTableController {

    @RequestMapping("")
    public ModelAndView index(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("menu", getMenu(httpSession));
        return modelAndView;
    }

    @GetMapping("{group}/{name}")
    public ModelAndView list(@PathVariable("group") String group, @PathVariable("name") String name, HttpSession httpSession) {
        RegisterInfo registerInfo = verify(group, name);
        if (registerInfo == null) {
            return null;
        }
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("group", group);
        modelAndView.addObject("name", name);
        modelAndView.addObject("menu", getMenu(httpSession));
        modelAndView.addObject("registerInfo", registerInfo);
        generateSelectOptions(registerInfo, modelAndView);
        return modelAndView;
    }

    void generateSelectOptions(RegisterInfo registerInfo, ModelAndView modelAndView) {
        if (MapUtils.isNotEmpty(registerInfo.getForeignKeyInfoMap())) {
            Map<String, List> foreignInfoMap = new HashMap<>();
            for (Map.Entry<String, ForeignKeyInfo> stringForeignKeyInfoEntry : registerInfo.getForeignKeyInfoMap().entrySet()) {
                ForeignKeyInfo foreignKeyInfo = stringForeignKeyInfoEntry.getValue();
                String configName = foreignKeyInfo.getName();
                if (AdminConfig.registerInfoMap.containsKey(configName) && foreignKeyInfo.getSelector() != null) {
                    BaseService baseService = (BaseService) SpringUtil.getBean(AdminConfig.registerInfoMap.get(configName).getServiceClazz());
                    List list = baseService.select(foreignKeyInfo.getSelector());
                    foreignInfoMap.put(stringForeignKeyInfoEntry.getKey(), getSelectOptionList(list, foreignKeyInfo));
                } else if (CollectionUtils.isNotEmpty(foreignKeyInfo.getOptions())) {
                    foreignInfoMap.put(stringForeignKeyInfoEntry.getKey(), foreignKeyInfo.getOptions());
                }
            }
            modelAndView.addObject("foreignInfoMap", foreignInfoMap);
        }
    }

    @PostMapping("{group}/{name}")
    public PageUtil list(@PathVariable("group") String group, @PathVariable("name") String name, Integer draw, Integer start, Integer length) throws ErrorCompareValueException, ErrorParseSelectorException, NoSuchTableConfigException, UnsupportedEncodingException {
        RegisterInfo registerInfo = verify(group, name);
        if (registerInfo == null) {
            return null;
        }
        BaseService baseService = (BaseService) SpringUtil.getBean(registerInfo.getServiceClazz());
        Selector selector = getSelector(name, true);
        start = start == null ? 1 : start;
        length = length == null ? 10 : length;
        if (length != null) {
            start = start / length + 1;
        } else {
            start = start / 10 + 1;
        }
        Page page = baseService.selectPage(selector, start, length);
        return new PageUtil(draw, page);
    }

    @GetMapping("{group}/{name}/add")
    public ModelAndView addItem(@PathVariable("group") String group, @PathVariable("name") String name, HttpSession httpSession) {
        RegisterInfo registerInfo = verify(group, name);
        if (registerInfo == null) {
            return null;
        }
        ModelAndView modelAndView = new ModelAndView("addItem");
        modelAndView.addObject("group", group);
        modelAndView.addObject("name", name);
        modelAndView.addObject("menu", getMenu(httpSession));
        modelAndView.addObject("registerInfo", registerInfo);
        modelAndView.addObject("actionPath", "/admin/" + group + "/" + name + "/add");
        generateSelectOptions(registerInfo, modelAndView);
        return modelAndView;
    }

    RegisterInfo verify(String group, String name) {
        if (!AdminConfig.registerInfoMap.containsKey(name)) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        RegisterInfo registerInfo = AdminConfig.registerInfoMap.get(name);
        if (!group.equals(registerInfo.getGroup())) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return registerInfo;
    }

    @PostMapping("{group}/{name}/add")
    public ModelAndView addItemCommit(@PathVariable("group") String group, @PathVariable("name") String name, HttpSession httpSession) {
        RegisterInfo registerInfo = verify(group, name);
        if (registerInfo == null) {
            return null;
        }
        Object parameter = getParameter(registerInfo.getBeanClazz(), true);
        BaseService baseService = (BaseService) SpringUtil.getBean(registerInfo.getServiceClazz());
        int success = baseService.insertSelective(parameter);
        ProcessResult processResult = null;
        if (success == 1) {
            processResult = ProcessResult.processSuccess(registerInfo.getShowName() + "插入成功");
        } else {
            processResult = ProcessResult.processFailed(registerInfo.getShowName() + "插入失败");
        }
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("group", group);
        modelAndView.addObject("name", name);
        modelAndView.addObject("menu", getMenu(httpSession));
        modelAndView.addObject("registerInfo", registerInfo);
        modelAndView.addObject("processResult", processResult);
        generateSelectOptions(registerInfo, modelAndView);
        return modelAndView;
    }

    @GetMapping("{group}/{name}/{id}/update")
    public ModelAndView selectOne(@PathVariable("group") String group, @PathVariable("name") String name, @PathVariable("id") String id, HttpSession httpSession) {
        RegisterInfo registerInfo = verify(group, name);
        if (registerInfo == null) {
            return null;
        }
        BaseService baseService = (BaseService) SpringUtil.getBean(registerInfo.getServiceClazz());
        List list = baseService.selectById(id);
        if (CollectionUtils.isNotEmpty(list)) {
            if (list.size() == 1) {
                Object o = list.get(0);
                Class<?> aClass = o.getClass();
                for (BeanPropertyInfo beanPropertyInfo : registerInfo.getBeanInfo().getBeanPropertyInfos()) {
                    try {
                        Field declaredField = aClass.getDeclaredField(beanPropertyInfo.getPropertyName());
                        declaredField.setAccessible(true);
                        if ("java.util.Date".equals(beanPropertyInfo.getPropertyType())) {
                            beanPropertyInfo.setValue(df.format(declaredField.get(o)));
                        } else {
                            beanPropertyInfo.setValue(declaredField.get(o));
                        }
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                response.setStatus(HttpServletResponse.SC_MULTIPLE_CHOICES);
                return null;
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        ModelAndView modelAndView = new ModelAndView("updateItem");
        modelAndView.addObject("group", group);
        modelAndView.addObject("name", name);
        modelAndView.addObject("menu", getMenu(httpSession));
        modelAndView.addObject("registerInfo", registerInfo);
        modelAndView.addObject("actionPath", "/admin/" + group + "/" + name + "/" + id + "/update");
        generateSelectOptions(registerInfo, modelAndView);
        return modelAndView;
    }

    @PostMapping("{group}/{name}/{id}/update")
    public ModelAndView updateItemCommit(@PathVariable("group") String group, @PathVariable("name") String name, @PathVariable("id") String id, HttpSession httpSession) {
        RegisterInfo registerInfo = verify(group, name);
        if (registerInfo == null) {
            return null;
        }
        Object obj = getParameter(registerInfo.getBeanClazz(), true);
        BaseService baseService = (BaseService) SpringUtil.getBean(registerInfo.getServiceClazz());
        int success = baseService.updateById(obj);
        ProcessResult processResult = null;
        if (success == 1) {
            processResult = ProcessResult.processSuccess(registerInfo.getShowName() + "更新成功");
        } else {
            processResult = ProcessResult.processFailed(registerInfo.getShowName() + "更新失败");
        }
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("group", group);
        modelAndView.addObject("name", name);
        modelAndView.addObject("menu", getMenu(httpSession));
        modelAndView.addObject("registerInfo", registerInfo);
        modelAndView.addObject("processResult", processResult);
        generateSelectOptions(registerInfo, modelAndView);
        return modelAndView;
    }

    @GetMapping("{group}/{name}/{id}/delete")
    public ModelAndView deleteItemCommit(@PathVariable("group") String group, @PathVariable("name") String name, @PathVariable("id") String id, HttpSession httpSession) {
        RegisterInfo registerInfo = verify(group, name);
        if (registerInfo == null) {
            return null;
        }
        BaseService baseService = (BaseService) SpringUtil.getBean(registerInfo.getServiceClazz());
        int success = baseService.deleteById(id);
        ProcessResult processResult = null;
        if (success == 1) {
            processResult = ProcessResult.processSuccess(registerInfo.getShowName() + "删除成功");
        } else {
            processResult = ProcessResult.processFailed(registerInfo.getShowName() + "删除失败");
        }
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("group", group);
        modelAndView.addObject("name", name);
        modelAndView.addObject("menu", getMenu(httpSession));
        modelAndView.addObject("registerInfo", registerInfo);
        modelAndView.addObject("processResult", processResult);
        generateSelectOptions(registerInfo, modelAndView);
        return modelAndView;
    }

    /**
     * 修改某个字段
     *
     * @param name
     * @param id
     */
    @PostMapping("{group}/{name}/{id}/patch")
    public void patch(@PathVariable("name") String name, @PathVariable("id") String id) {

    }

    @GetMapping("{group}/{name}/multiDelete/{idList}")
    public ModelAndView multiDelete(@PathVariable("group") String group, @PathVariable("name") String name, @PathVariable("idList") String idList, HttpSession httpSession) {
        RegisterInfo registerInfo = verify(group, name);
        if (registerInfo == null) {
            return null;
        }
        BaseService baseService = (BaseService) SpringUtil.getBean(registerInfo.getServiceClazz());
        String[] ids = StringUtils.isNotEmpty(idList) ? idList.split(",") : null;
        int n = ids == null ? 0 : ids.length, count = 0;
        if (ids != null) {
            for (String id : ids) {
                int success = baseService.deleteById(id);
                if (success == 1) {
                    count++;
                }
            }
        }
        ProcessResult processResult = null;
        if (n == count) {
            if (n == 0) {
                processResult = ProcessResult.processFailed("提交的id列表为空");
            } else {
                processResult = ProcessResult.processSuccess("已成功删除" + n + "个" + registerInfo.getShowName());
            }
        } else {
            processResult = ProcessResult.processFailed("提交删除" + n + "个" + registerInfo.getShowName() + ",成功删除" + count + "个");
        }
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("group", group);
        modelAndView.addObject("name", name);
        modelAndView.addObject("menu", getMenu(httpSession));
        modelAndView.addObject("registerInfo", registerInfo);
        modelAndView.addObject("processResult", processResult);
        generateSelectOptions(registerInfo, modelAndView);
        return modelAndView;
    }

    public static void main(String[] args) {
        String[] split = "".split(",");
        System.out.println("skhdflkjasl_lskdjf_slkdfj_".replaceAll("_([a-z]{1,1})", "$1"));
    }
}
