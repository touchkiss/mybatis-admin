package com.touchkiss.mybatis.base.service.impl;

import com.touchkiss.mybatis.base.bean.DTable;
import com.touchkiss.mybatis.base.service.DTableService;
import com.touchkiss.mybatis.base.dao.auto.DTableAutoDao;
import com.touchkiss.mybatis.sqlbuild.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Date;

/**
 * .service业务层实现
 *
 * @author Touchkiss
 **/
@Service
public class DTableServiceImpl extends BaseServiceImpl<DTable> implements DTableService {
    @Autowired
    private DTableAutoDao dao;

    /**
     * 根据主键 查询
     */
    @Override
    public List<DTable> selectByPid(Date pid){
        return dao.selectByPid(pid);
    }

    /**
     * 根据主键 更新
     */
    @Override
    public int updateByPid(DTable bean){
        return dao.updateByPid(bean);
    }

    /**
     * 根据主键 更新指定字段
     */
    @Override
    public int updateSelectiveByPid(DTable bean){
        return dao.updateSelectiveByPid(bean);
    }

    /**
     * 根据主键 删除
     */
    @Override
    public int deleteByPid(Date pid){
        return dao.deleteByPid(pid);
    }

    /**
     * 根据主键 查询
     */
    @Override
    public List<DTable> selectByIdAndPid(Integer id, Date pid){
        return dao.selectByIdAndPid(id, pid);
    }

    /**
     * 根据主键 更新
     */
    @Override
    public int updateByIdAndPid(DTable bean){
        return dao.updateByIdAndPid(bean);
    }

    /**
     * 根据主键 更新指定字段
     */
    @Override
    public int updateSelectiveByIdAndPid(DTable bean){
        return dao.updateSelectiveByIdAndPid(bean);
    }

    /**
     * 根据主键 删除
     */
    @Override
    public int deleteByIdAndPid(Integer id, Date pid){
        return dao.deleteByIdAndPid(id, pid);
    }
}