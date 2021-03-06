package com.gl.police.service;

import com.gl.police.daoAPI.PersonMapper;
import com.gl.police.entity.BaseReqPage;
import com.gl.police.entity.BaseResPage;
import com.gl.police.entity.Person;
import com.gl.police.serviceAPI.PersonService;
import com.gl.police.util.Encoder;
import com.gl.police.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: leifeng.ye
 * @date: 2020-01-11
 * @desc:
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper dao;

    @Override
    public int addPerson(Person person) {
        person.setId(UUID.getUUID());
        person.setuId(Encoder.encoder(person.getuId()));
        if(person.getPhone()!=null){
            person.setPhone(Encoder.encoder(person.getPhone()));
        }
        String img=savePersonPhoto(person);
        person.setImg(img);
        return dao.insertSelective(person);
    }

    @Override
    public String savePersonPhoto(Person person) {
        String basePath="http://120.27.246.207:8765/personPhoto/"+person.getuId();
        String s=this.getClass().getClassLoader().getResource("").getPath();
        int index=s.lastIndexOf("target");
        String savePath=s.substring(0,index)+"src/main/resources/static/personPhoto/"+person.getuId();
        MultipartFile file=person.getPhoto();
        String fileName=file.getOriginalFilename();
        String type=fileName.substring(fileName.lastIndexOf('.'),fileName.length());
        String path=basePath+type;
        try{
            file.transferTo(new File(savePath+type));
        }catch (Exception e){
            e.printStackTrace();
        }
        return path;
    }

    @Override
    public BaseResPage selectPageList(BaseReqPage page) {
        int total=selectListCount();
        BaseResPage<Person> b=new BaseResPage();
        b.setPageNo(page.getPageNo());
        b.setPageSize(page.getPageSize());
        b.setTotal(total);
        page.setPageNo(page.getPageNo()*page.getPageSize());
        ArrayList<Person> list=(ArrayList<Person>)dao.selectPageList(page);
        for(Person a:list){
            a.setuId(Encoder.decoder(a.getuId()));
            if(a.getPhone()!=null){
                a.setPhone(Encoder.decoder(a.getPhone()));
            }
        }
        b.setResult(list);
        return b;
    }

    @Override
    public Integer selectListCount() {
        return dao.selectListCount();
    }

    @Override
    public List selectList() {
        ArrayList<Person> list=(ArrayList<Person>)dao.selectList();
        for(Person a:list){
            a.setuId(Encoder.decoder(a.getuId()));
            if(a.getPhone()!=null){
                a.setPhone(Encoder.decoder(a.getPhone()));
            }
        }
        return list;
    }

    @Override
    public Person selectPersonById(String id) {
        Person person=dao.selectById(id);
        if(person!=null){
            person.setuId(Encoder.decoder(person.getuId()));
            if(person.getPhone()!=null){
                person.setPhone(Encoder.decoder(person.getPhone()));
            }
        }
        return person;
    }

    @Override
    public int updateSelective(Person person) {
        person.setuId(Encoder.encoder(person.getuId()));
        if(person.getPhone()!=null){
            person.setPhone(Encoder.encoder(person.getPhone()));
        }
        String img=savePersonPhoto(person);
        person.setImg(img);
        return dao.updateSelective(person);
    }

    @Override
    public Person selectPersonByPhone(String phone) {
        Person person=dao.selectPersonByPhone(Encoder.encoder(phone));
        if(person!=null){
            if(person.getuId()!=null){
                person.setId(Encoder.decoder(person.getuId()));
            }
            if(person.getPhone()!=null){
                person.setPhone(Encoder.decoder(person.getPhone()));
            }
        }
        return person;
    }

    @Override
    public int deletePerson(String id) {
        return dao.delete(id);
    }

    @Override
    public List selectLimitPerson(Person person) {
        ArrayList<Person> list=(ArrayList<Person>) dao.selectPersonSelective(person);
        for(Person a:list){
            a.setuId(Encoder.decoder(a.getuId()));
            if(a.getPhone()!=null){
                a.setPhone(Encoder.decoder(a.getPhone()));
            }
        }
        return list;
    }

    @Override
    public Person selectPersonByUid(String uId) {
        Person person=dao.selectPersonByUid(Encoder.encoder(uId));
        if(person!=null){
            if(person.getPhone()!=null){
                person.setPhone(Encoder.decoder(person.getPhone()));
            }
        }
        return person;
    }

    @Override
    public List selectPersonByCid(String cId) {
        ArrayList<Person> list=(ArrayList<Person>) dao.selectPersonByCid(cId);
        for(Person a:list){
            a.setuId(Encoder.decoder(a.getuId()));
            if(a.getPhone()!=null){
                a.setPhone(Encoder.decoder(a.getPhone()));
            }
        }
        return list;
    }
}
