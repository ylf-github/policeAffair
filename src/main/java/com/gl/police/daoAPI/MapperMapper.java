package com.gl.police.daoAPI;

import com.gl.police.entity.MapperKey;
import org.springframework.stereotype.Repository;

@Repository
public interface MapperMapper {
    int deleteByPrimaryKey(MapperKey key);

    int insert(MapperKey record);

    int insertSelective(MapperKey record);
}