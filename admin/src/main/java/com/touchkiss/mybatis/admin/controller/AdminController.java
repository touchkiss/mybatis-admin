package com.touchkiss.mybatis.admin.controller;

import com.github.pagehelper.Page;
import com.touchkiss.mybatis.admin.annotation.AdminColumn;
import com.touchkiss.mybatis.admin.bean.BeanPropertyInfo;
import com.touchkiss.mybatis.admin.bean.ForeignKeyInfo;
import com.touchkiss.mybatis.admin.bean.PageUtil;
import com.touchkiss.mybatis.admin.bean.RegisterInfo;
import com.touchkiss.mybatis.admin.config.AdminConfig;
import com.touchkiss.mybatis.admin.config.Constants;
import com.touchkiss.mybatis.admin.exception.ErrorCompareValueException;
import com.touchkiss.mybatis.admin.exception.ErrorParseSelectorException;
import com.touchkiss.mybatis.admin.exception.NoSuchTableConfigException;
import com.touchkiss.mybatis.admin.utils.SpringUtil;
import com.touchkiss.mybatis.sqlbuild.selector.Selector;
import com.touchkiss.mybatis.sqlbuild.service.BaseService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author Touchkiss
 * @create: 2019-06-20 15:49
 */
@RestController
@RequestMapping("admin")
public class AdminController extends JQueryDataTableController {
    @Value("${kindeditor.imgupload.path:/admin/imgUpload}")
    private String imgUploadPath;
    @Value("${kindeditor.imageupload.storepath:default}")
    private String imgStorePath;

    @RequestMapping("")
    public ModelAndView index(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("admin/index");
        modelAndView.addObject("menu", getMenu(httpSession));
        return modelAndView;
    }

    @GetMapping("{group}/{name}")
    public ModelAndView list(@PathVariable("group") String group, @PathVariable("name") String name, HttpSession httpSession) {
        RegisterInfo registerInfo = verify(group, name);
        if (registerInfo == null) {
            return null;
        }
        ModelAndView modelAndView = new ModelAndView("admin/list");
        modelAndView.addObject("group", group);
        modelAndView.addObject("name", name);
        modelAndView.addObject("menu", getMenu(httpSession));
        modelAndView.addObject("registerInfo", registerInfo);
        generateSelectOptions(registerInfo, modelAndView);
        return modelAndView;
    }

    void generateSelectOptions(RegisterInfo registerInfo, ModelAndView modelAndView) {
        if (MapUtils.isNotEmpty(registerInfo.getForeignKeyInfoMap())) {
            Map<String, List> foreignInfoMap = new HashMap<>(registerInfo.getForeignKeyInfoMap().size() * 2 + 1);
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
        Page<Map> result = new Page();
        List resultList = page.getResult();
        if (CollectionUtils.isNotEmpty(resultList)) {
            for (Object o : resultList) {
                Class<?> aClass = o.getClass();
                Field[] declaredFields = aClass.getDeclaredFields();
                Map<String, Object> valueMap = new HashMap<>(declaredFields.length * 2 + 1);
                for (Field field : declaredFields) {
                    try {
                        field.setAccessible(true);
                        Object value = field.get(o);
                        String fieldName = field.getName();
                        if (field.isAnnotationPresent(AdminColumn.class)) {
                            AdminColumn adminColumn = field.getAnnotation(AdminColumn.class);
                            if ("java.util.Date".equals(field.getType().getTypeName()) && value != null) {
                                switch (adminColumn.jdbctype()) {
                                    case "TIME":
                                        valueMap.put(fieldName, Constants.DEFAULT_TIME_FORMAT.format(value));
                                        break;
                                    case "DATE":
                                        valueMap.put(fieldName, Constants.DEFAULT_DATE_FORMAT.format(value));
                                        break;
                                    default:
                                        valueMap.put(fieldName, Constants.DEFAULT_DATETIME_FORMAT.format(value));
                                        break;
                                }
                            } else {
                                valueMap.put(fieldName, value);
                            }
                        } else {
                            valueMap.put(fieldName, value);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                result.add(valueMap);
            }
        }
        result.setTotal(page.getTotal());
        System.out.println(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
        return new PageUtil(draw, result);
    }

    @GetMapping("{group}/{name}/add")
    public ModelAndView addItem(@PathVariable("group") String group, @PathVariable("name") String name, HttpSession httpSession) {
        RegisterInfo registerInfo = verify(group, name);
        if (registerInfo == null) {
            return null;
        }
        ModelAndView modelAndView = new ModelAndView("admin/addItem");
        modelAndView.addObject("group", group);
        modelAndView.addObject("name", name);
        modelAndView.addObject("menu", getMenu(httpSession));
        modelAndView.addObject("registerInfo", registerInfo);
        modelAndView.addObject("actionPath", "/admin/" + group + "/" + name + "/add");
        modelAndView.addObject("imgUploadPath", imgUploadPath);
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
        ModelAndView modelAndView = new ModelAndView("admin/list");
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
        Object o = baseService.selectOneByID(id);
        if (o != null) {
            Class<?> aClass = o.getClass();
            Map<String, Object> valueMap = new HashMap<>(registerInfo.getBeanInfo().getBeanPropertyInfos().length * 2 + 1);
            for (BeanPropertyInfo beanPropertyInfo : registerInfo.getBeanInfo().getBeanPropertyInfos()) {
                try {
                    Field declaredField = aClass.getDeclaredField(beanPropertyInfo.getPropertyName());
                    declaredField.setAccessible(true);
                    Object value = declaredField.get(o);
                    if ("java.util.Date".equals(beanPropertyInfo.getPropertyType()) && value != null) {
                        switch (beanPropertyInfo.getJdbctype()) {
                            case "TIME":
                                valueMap.put(beanPropertyInfo.getPropertyName(), Constants.DEFAULT_TIME_FORMAT.format(value));
                                break;
                            case "DATE":
                                valueMap.put(beanPropertyInfo.getPropertyName(), Constants.DEFAULT_DATE_FORMAT.format(value));
                                break;
                            default:
                                valueMap.put(beanPropertyInfo.getPropertyName(), Constants.DEFAULT_DATETIME_FORMAT.format(value));
                                break;
                        }
                    } else {
                        valueMap.put(beanPropertyInfo.getPropertyName(), value);
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            ModelAndView modelAndView = new ModelAndView("admin/updateItem");
            modelAndView.addObject("group", group);
            modelAndView.addObject("name", name);
            modelAndView.addObject("menu", getMenu(httpSession));
            modelAndView.addObject("registerInfo", registerInfo);
            modelAndView.addObject("actionPath", "/admin/" + group + "/" + name + "/" + id + "/update");
            modelAndView.addObject("valueMap", valueMap);
            modelAndView.addObject("imgUploadPath", imgUploadPath);
            generateSelectOptions(registerInfo, modelAndView);
            return modelAndView;
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    @PostMapping("{group}/{name}/{id}/update")
    public ModelAndView updateItemCommit(@PathVariable("group") String group, @PathVariable("name") String name, @PathVariable("id") String id, HttpSession httpSession) {
        RegisterInfo registerInfo = verify(group, name);
        if (registerInfo == null) {
            return null;
        }
        Object obj = getParameter(registerInfo.getBeanClazz(), true);
        BaseService baseService = (BaseService) SpringUtil.getBean(registerInfo.getServiceClazz());
        int success = baseService.updateOneByID(obj);
        ProcessResult processResult = null;
        if (success == 1) {
            processResult = ProcessResult.processSuccess(registerInfo.getShowName() + "更新成功");
        } else {
            processResult = ProcessResult.processFailed(registerInfo.getShowName() + "更新失败");
        }
        ModelAndView modelAndView = new ModelAndView("admin/list");
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
        int success = baseService.deleteOneByID(id);
        ProcessResult processResult = null;
        if (success == 1) {
            processResult = ProcessResult.processSuccess(registerInfo.getShowName() + "删除成功");
        } else {
            processResult = ProcessResult.processFailed(registerInfo.getShowName() + "删除失败");
        }
        ModelAndView modelAndView = new ModelAndView("admin/list");
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
                int success = baseService.deleteOneByID(id);
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
        ModelAndView modelAndView = new ModelAndView("admin/list");
        modelAndView.addObject("group", group);
        modelAndView.addObject("name", name);
        modelAndView.addObject("menu", getMenu(httpSession));
        modelAndView.addObject("registerInfo", registerInfo);
        modelAndView.addObject("processResult", processResult);
        generateSelectOptions(registerInfo, modelAndView);
        return modelAndView;
    }

    @PostMapping("imgUpload")
    public Map<String, Object> imgUpload(@RequestParam(name = "imgFile", required = false) MultipartFile imgFile) {
        if (imgFile != null && imgFile.getSize() > 0) {
            String imgId = UUID.randomUUID().toString();
            try {
                InputStream inputStream = imgFile.getInputStream();
                String folderPath = "default".equals(imgStorePath) ? getImgStorePath() : imgStorePath;
                File folder = new File(folderPath);
                if (!folder.exists() || !folder.isDirectory()) {
                    if (!folder.mkdirs()) {
                        throw new IOException("无法创建文件夹");
                    }
                }
                String filepath = folderPath + imgId + ".jpg";
                File f = new File(filepath);
                if (!f.exists()) {
                    f.createNewFile();
                }
                FileOutputStream fos = new FileOutputStream(f);
                byte[] b = new byte[1024];
                int length;
                while ((length = inputStream.read(b)) > 0) {
                    fos.write(b, 0, length);
                }
                inputStream.close();
                fos.close();
                Map<String, Object> map = new HashMap(7);
                map.put("error", 0);
                System.out.println(filepath);
                String sp = "classes" + (filepath.indexOf("\\") > -1 ? "\\" : "/") + "static";
                filepath = filepath.substring(filepath.indexOf(sp) + sp.length());
                map.put("url", filepath.replaceAll("\\\\", "/"));
                return map;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private String getImgStorePath() {
        String classpath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        if (classpath.startsWith("/")) {
            classpath = classpath.substring(1);
        }
        return classpath + "static/images/upload/";
    }
}
