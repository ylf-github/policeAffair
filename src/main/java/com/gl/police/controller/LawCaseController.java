package com.gl.police.controller;

import com.gl.police.entity.*;
import com.gl.police.serviceAPI.LawCaseService;
import com.gl.police.serviceAPI.PersonService;
import com.gl.police.util.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author: leifeng.ye
 * @date: 2020-01-12
 * @desc:
 */
@RestController
@RequestMapping("/api/v1")
public class LawCaseController {

    @Autowired
    private LawCaseService service;

    @Autowired
    private PersonService personService;

    @RequestMapping("/addLawCase")
    @CrossOrigin
    public Response addLawCase(@RequestBody LawCase lawCase){
        int n=service.addLawCase(lawCase);
        if(n>0){
            return Response.success(null,"添加成功");
        }else{
            return Response.error("添加失败");
        }
    }

    @RequestMapping("/selectPageLawCase")
    @CrossOrigin
    public Response selectPageLawCase(@RequestBody BaseReqPage page){
        BaseResPage<LawCase> res=service.selectPageCase(page);
        ArrayList list=new ArrayList();
        list.add(res);
        return Response.success(list,"查询成功");
    }

    @RequestMapping("/selectLimitLawCase")
    @CrossOrigin
    public Response selectLimitLawCase(@RequestBody LawCase lawCase){
        ArrayList<LawCase> list=(ArrayList<LawCase>) service.selectLimitLawCase(lawCase);
        return Response.success(list,"查询成功");
    }

    @RequestMapping("/deleteLawCase")
    @CrossOrigin
    public Response deleteLawCase(@RequestBody Map map){
        String cId=(String)map.get("cId");
        int n=service.deleteLawCase(cId);
        if(n>0){
            return Response.success(null,"删除成功");
        }else{
            return Response.error("删除失败");
        }
    }

    @RequestMapping("/updateLawCase")
    @CrossOrigin
    public Response updateLawCase(@RequestBody LawCase lawCase){
        int n=service.updateLawCase(lawCase);
        if(n>0){
            return Response.success(null,"更新成功");
        }else{
            return Response.error("更新失败");
        }
    }

    @RequestMapping("/selectLawCaseByPerson")
    @CrossOrigin
    public Response selectLawCaseByPerson(@RequestBody Map map){
        String uId=(String)map.get("uId");
        ArrayList<LawCase> list=(ArrayList<LawCase>) service.selectLawCaseByPerson(uId);
        return Response.success(list,"查询成功");
    }

    @RequestMapping("/selectLawCase")
    @CrossOrigin
    public Response selectLawCase(@RequestBody Map map){
        String cId=(String)map.get("cId");
        LawCase lawCase=service.selectLawCase(cId);
        if(lawCase==null){
            return Response.error("案件不存在");
        }
        ArrayList<Person> persons=(ArrayList<Person>) personService.selectPersonByCid(cId);
        ArrayList list=new ArrayList();
        list.add(lawCase);
        list.add(persons);
        return Response.success(list,"查询成功");
    }
}
