package com.gl.police.daoAPI;

import com.gl.police.entity.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonMapper {
    int deleteByPrimaryKey(String uId);

    int insert(Person record);

    int insertSelective(Person record);

    Person selectByPrimaryKey(String uId);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);
}