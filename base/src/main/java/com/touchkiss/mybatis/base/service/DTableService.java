package com.touchkiss.mybatis.base.service;

import com.touchkiss.mybatis.sqlbuild.service.BaseService;
import java.util.List;
import com.touchkiss.mybatis.base.bean.DTable;
import java.util.Date;

/**
 * .service业务层接口
 *
 * @author Touchkiss
 **/
public interface DTableService extends BaseService<DTable>{


    /**
     * 根据主键 查询
     */
    List<DTable> selectByPid(Date pid);

    /**
      * 根据主键 更新
      */
    int updateByPid(DTable bean);

    /**
      * 根据主键 更新指定字段
      */
    int updateSelectiveByPid(DTable bean);

    /**
       * 根据主键 删除
       */
    int deleteByPid(Date pid);

    /**
     * 根据主键 查询
     */
    List<DTable> selectByIdAndPid(Integer id, Date pid);

    /**
     * 根据主键 更新
     */
    int updateByIdAndPid(DTable bean);

    /**
     * 根据主键 更新指定字段
     */
    int updateSelectiveByIdAndPid(DTable bean);

    /**
     * 根据主键 删除
     */
    int deleteByIdAndPid(Integer id, Date pid);
}