package com.gl.police.daoAPI;

import com.gl.police.entity.BaseReqPage;
import com.gl.police.entity.LawCase;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LawCaseMapper {

    int insertSelective(LawCase record);

    List selectPageLawCase(BaseReqPage page);

    int selectLawCaseCount();

    List selectLawCaseSelective(LawCase lawCase);

    int deleteLawCase(String cId);

    int updateSelective(LawCase lawCase);

    List selectLawCaseByPerson(String uId);

    LawCase selectLawCase(String cId);
}