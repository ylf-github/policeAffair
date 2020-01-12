package com.gl.police.daoAPI;

import com.gl.police.entity.MapperKey;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MapperMapper {
    int delete(MapperKey key);

    int insert(MapperKey record);

    List selectMapper(String cId);
}