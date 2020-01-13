package com.gl.police.controller;

import com.alibaba.fastjson.JSONObject;
import com.gl.police.config.PoliceRedisTemplate;
import com.gl.police.entity.Admin;
import com.gl.police.entity.Response;
import com.gl.police.serviceAPI.AdminService;
import com.gl.police.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: leifeng.ye
 * @date: 2020-01-10
 * @desc:
 */
@RequestMapping("/api/v1")
@RestController
public class AdminController {

    @Autowired
    private AdminService service;

    @Autowired
    private MessageController messageController;

    @Autowired
    public PoliceRedisTemplate factory;

    @RequestMapping("/login")
    @CrossOrigin
    public Response login(@RequestBody Admin admin){
        if(admin.getAdminid()==null||admin.getPassword()==null){
            return Response.error("必要参数为空");
        }
        if(service.isLegalAccount(admin)){
            ArrayList list=new ArrayList();
            JSONObject json=new JSONObject();
            json.put("token", Token.getToken(admin.getAdminid()));
            list.add(json);
            return Response.success(list,"登录成功");
        }else {
            return Response.error("密码错误");
        }
    }

    @RequestMapping("/loginByPhone")
    @CrossOrigin
    public Response loginByPhone(@RequestBody Map m)throws Exception{
        String phone=(String) m.get("phone");
        if (phone == null) {
            return Response.error("必要参数为空");
        }
        boolean f = service.isLegalByPhone(phone);
        if(f){
            HashMap map=new HashMap();
            map.put("phone",phone);
            messageController.sendCode(map);
            return Response.success(null,"发送成功");
        }
        else {
            return Response.error("没有此手机号码绑定的用户");
        }

    }

    @RequestMapping("/authByPhoneCode")
    @CrossOrigin
    public Response authByPhoneCode(@RequestBody Map map)throws Exception{
        String phone=(String) map.get("phone");
        String userCode=(String) map.get("code");
        RedisTemplate redisTemplate=factory.getRedisTemplate();
        String realCode=(String) redisTemplate.opsForValue().get(phone);
        if(realCode==null){
            return Response.error("验证码已失效");
        }
        else{
            if(realCode.equals(userCode)){
                String token = Token.getToken(phone);
                JSONObject ticket = new JSONObject();
                ticket.put("token", token);
                ArrayList<JSONObject> list = new ArrayList<>();
                list.add(ticket);
                redisTemplate.delete(phone);
                return Response.success(list, "登录成功");
            }
            else{
                return Response.error("验证码不正确");
            }
        }
    }
}
