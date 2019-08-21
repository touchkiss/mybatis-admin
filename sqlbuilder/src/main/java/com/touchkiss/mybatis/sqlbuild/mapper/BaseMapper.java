package com.touchkiss.mybatis.sqlbuild.mapper;

import com.github.pagehelper.Page;
import com.touchkiss.mybatis.sqlbuild.condition.original.PreparedCondition;
import com.touchkiss.mybatis.sqlbuild.selector.SelectMetadata;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author Touchkiss
 * @create: 2019-06-20 18:19
 */
public interface BaseMapper<T> {
    T selectOneByID(@Param("ids") Object... ids);

    List<T> selectAll();

    List<T> select(@Param("meta") SelectMetadata metadata);

    Page<T> selectPage(@Param("meta") SelectMetadata metadata);

    Map selectForMap(@Param("meta") SelectMetadata metadata);

    List<Map> selectForListMap(@Param("meta") SelectMetadata metadata);

    Page<Map> selectPageForListMap(@Param("meta") SelectMetadata metadata);

    int insert(@Param("record") T record);

    int insertSelective(@Param("record") T record);

    int update(@Param("record") T record, @Param("wheres") List<PreparedCondition> conditions);

    int updateSelective(@Param("record") T record, @Param("wheres") List<PreparedCondition> conditions);

    int delete(@Param("wheres") List<PreparedCondition> conditions);

    int deleteOneByID(@Param("ids") Object... ids);

    T selectOne(@Param("record") T bean);

    T selectOne(@Param("meta") SelectMetadata metadata);

    int updateOneByID(@Param("record") T bean,@Param("ids") Object... ids);

    int updateOneSelectiveByID(@Param("record") T bean,@Param("ids") Object... ids);
}
