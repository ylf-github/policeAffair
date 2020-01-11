package com.gl.police.controller;

import com.gl.police.entity.BaseReqPage;
import com.gl.police.entity.Person;
import com.gl.police.entity.Response;
import com.gl.police.serviceAPI.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author: leifeng.ye
 * @date: 2020-01-11
 * @desc:
 */
@RestController
@RequestMapping("/api/v1")
public class PersonController {

    @Autowired
    private PersonService service;

    @RequestMapping("/addPerson")
    @CrossOrigin
    public Response addPerson(Person person){
        int n=service.addPerson(person);
        if(n>0){
            return Response.success(null,"添加成功");
        }else{
            return Response.error("添加失败");
        }
    }

    @RequestMapping("/selectPageList")
    @CrossOrigin
    public Response selectPerson(@RequestBody BaseReqPage page){
        ArrayList list=new ArrayList();
        list.add(service.selectPageList(page));
        return Response.success(list,"查询成功");
    }

    @RequestMapping("/selectList")
    @CrossOrigin
    public Response selectList(){
        ArrayList<Person> list=(ArrayList<Person>) service.selectList();
        return Response.success(list,"添加成功");
    }

    @RequestMapping("/selectPerson")
    @CrossOrigin
    public Response selectPerson(@RequestBody Map map){
        String uId=(String) map.get("uId");
        Person person=service.selectPersonById(uId);
        ArrayList list=new ArrayList();
        list.add(person);
        return Response.success(list,"查询成功");
    }
}
