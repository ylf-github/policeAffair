package com.gl.police.daoAPI;

import com.gl.police.entity.LawCase;
import org.springframework.stereotype.Repository;

@Repository
public interface LawCaseMapper {
    int deleteByPrimaryKey(String cId);

    int insert(LawCase record);

    int insertSelective(LawCase record);

    LawCase selectByPrimaryKey(String cId);

    int updateByPrimaryKeySelective(LawCase record);

    int updateByPrimaryKey(LawCase record);
}