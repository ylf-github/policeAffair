package com.gl.police.daoAPI;

import com.gl.police.entity.BaseReqPage;
import com.gl.police.entity.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonMapper {

    int insertSelective(Person record);

    List selectPageList(BaseReqPage page);

    Integer selectListCount();

    List selectList();

    Person selectById(String uId);
}