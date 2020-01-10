package com.gl.police.serviceAPI;

import com.gl.police.entity.Admin;

import java.util.List;

/**
 * @author: leifeng.ye
 * @date: 2020-01-10
 * @desc:
 */
public interface AdminService {
    List selectList();        //返回所有admin
    boolean isLegalAccount(Admin admin); //判断是否为合法账户
    Admin selectByPhone(String phone);  //根据id获取用户
    boolean isLegalByPhone(String phone); //判断是否为合法手机号
}
