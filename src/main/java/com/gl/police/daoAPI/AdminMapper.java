package com.gl.police.daoAPI;

import com.gl.police.entity.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminMapper {
    Admin selectByPhone(String phone);

    List selectList();
}