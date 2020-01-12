package com.gl.police.serviceAPI;

import com.gl.police.entity.BaseReqPage;
import com.gl.police.entity.BaseResPage;
import com.gl.police.entity.LawCase;

import java.util.List;


/**
 * @author: leifeng.ye
 * @date: 2020-01-12
 * @desc:
 */
public interface LawCaseService {
    int addLawCase(LawCase lawCase); //添加案件
    BaseResPage selectPageCase(BaseReqPage page);  //分页查询案件
    int selectLawCaseCount();  //查询案件数量
    List selectLimitLawCase(LawCase lawCase);  //根据条件查询案件
    int deleteLawCase(String cId); //删除案件
    int updateLawCase(LawCase lawCase); //更新案件
    List selectLawCaseByPerson(String uId); //根据用户查案件
    LawCase selectLawCase(String cId);  //查询案件
}