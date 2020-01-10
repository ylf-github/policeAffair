package com.gl.police.service;

import com.gl.police.daoAPI.AdminMapper;
import com.gl.police.entity.Admin;
import com.gl.police.serviceAPI.AdminService;
import com.gl.police.util.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: leifeng.ye
 * @date: 2020-01-10
 * @desc:
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper dao;

    @Override
    public List selectList() {
        return dao.selectList();
    }

    @Override
    public boolean isLegalAccount(Admin admin) {
        ArrayList<Admin> list=(ArrayList<Admin>)selectList();
        for(Admin a:list){
            if(a.getAdminid().equals(Encoder.encoder(admin.getAdminid()))
                    &&a.getPassword().equals(Encoder.encoder(admin.getPassword()))){
                return true;
            }
        }
        return false;
    }

    @Override
    public Admin selectByPhone(String phone) {
        return dao.selectByPhone(Encoder.encoder(phone));
    }

    @Override
    public boolean isLegalByPhone(String phone) {
        Admin t=selectByPhone(phone);
        if(t!=null){
            return true;
        }
        return false;
    }
}
