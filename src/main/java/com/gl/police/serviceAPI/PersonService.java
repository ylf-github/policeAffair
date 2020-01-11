package com.gl.police.serviceAPI;

import com.gl.police.entity.BaseReqPage;
import com.gl.police.entity.BaseResPage;
import com.gl.police.entity.Person;

import java.util.List;


/**
 * @author: leifeng.ye
 * @date: 2020-01-11
 * @desc:
 */
public interface PersonService {
    int addPerson(Person person);  //添加人员
    String savePersonPhoto(Person person);  //保存人员照片
    BaseResPage selectPageList(BaseReqPage page);  //分页查询人员
    Integer selectListCount();     //人员总数
    List selectList(); //返回所有人员
    Person selectPersonById(String uId);  //查询用户
}
