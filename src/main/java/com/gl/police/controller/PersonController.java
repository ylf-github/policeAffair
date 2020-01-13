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
        Person p=service.selectPersonByUid(person.getuId());
        if(p!=null){
            return Response.error("此身份证号已被其他人员占用");
        }
        if(person.getPhone()!=null){
            Person t=service.selectPersonByPhone(person.getPhone());
            if(t!=null){
                return Response.error("此手机号已被其他人员占用");
            }
        }
        int n=service.addPerson(person);
        if(n>0){
            return Response.success(null,"添加成功");
        }else{
            return Response.error("添加失败");
        }
    }

    @RequestMapping("/selectPersonPageList")
    @CrossOrigin
    public Response selectPerson(@RequestBody BaseReqPage page){
        ArrayList list=new ArrayList();
        list.add(service.selectPageList(page));
        return Response.success(list,"查询成功");
    }

    @RequestMapping("/selectPersonList")
    @CrossOrigin
    public Response selectList(){
        ArrayList<Person> list=(ArrayList<Person>) service.selectList();
        return Response.success(list,"添加成功");
    }

    @RequestMapping("/selectPerson")
    @CrossOrigin
    public Response selectPerson(@RequestBody Map map){
        String id=(String) map.get("id");
        Person person=service.selectPersonById(id);
        ArrayList list=new ArrayList();
        list.add(person);
        return Response.success(list,"查询成功");
    }

    @RequestMapping("/updatePerson")
    @CrossOrigin
    public Response updatePerson(Person person){
        if(person.getPhone()!=null){
            Person t=service.selectPersonByPhone(person.getPhone());
            if(t!=null){
                return Response.error("此手机号已被其他人员占用");
            }
        }
        int n=service.updateSelective(person);
        if(n>0){
            return Response.success(null,"更新成功");
        }
        else{
            return Response.error("更新失败");
        }
    }

    @RequestMapping("/deletePerson")
    @CrossOrigin
    public Response deletePerson(@RequestBody Map map){
        String id=(String)map.get("id");
        int n=service.deletePerson(id);
        if(n>0){
            return Response.success(null,"删除成功");
        }
        else{
            return Response.error("删除失败");
        }
    }

    @RequestMapping("/selectPersonSelective")
    @CrossOrigin
    public Response selectPersonSelective(@RequestBody Person person){
        ArrayList<Person> list=(ArrayList<Person>) service.selectLimitPerson(person);
        return Response.success(list,"查询成功");
    }

    @RequestMapping("/selectPersonByCid")
    @CrossOrigin
    public Response selectPersonByCid(@RequestBody Map map){
        String cId=(String)map.get("cId");
        ArrayList<Person> list=(ArrayList<Person>) service.selectPersonByCid(cId);
        return Response.success(list,"查询成功");
    }
}
