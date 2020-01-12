package com.gl.police.service;

import com.gl.police.daoAPI.LawCaseMapper;
import com.gl.police.daoAPI.MapperMapper;
import com.gl.police.entity.*;
import com.gl.police.serviceAPI.LawCaseService;
import com.gl.police.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: leifeng.ye
 * @date: 2020-01-12
 * @desc:
 */
@Service
public class LawCaseServiceImpl implements LawCaseService {

    @Autowired
    private LawCaseMapper dao;

    @Autowired
    private MapperMapper mapperDao;


    @Override
    public int addLawCase(LawCase lawCase) {
        String id=UUID.getUUID();
        lawCase.setcId(id);
        int n=dao.insertSelective(lawCase);
        String[] persons=lawCase.getPersons();
        for(String personId:persons){
            MapperKey key=new MapperKey();
            key.setcId(id);
            key.setuId(personId);
            mapperDao.insert(key);
        }
        return n;
    }

    @Override
    public BaseResPage selectPageCase(BaseReqPage page) {
        int total=selectLawCaseCount();
        BaseResPage<LawCase> res=new BaseResPage<>();
        res.setTotal(total);
        res.setPageNo(page.getPageNo());
        res.setPageSize(page.getPageSize());
        page.setPageNo(page.getPageNo()*page.getPageSize());
        ArrayList<LawCase> list=(ArrayList<LawCase>) dao.selectPageLawCase(page);
        res.setResult(list);
        return res;
    }

    @Override
    public int selectLawCaseCount() {
        return dao.selectLawCaseCount();
    }

    @Override
    public List selectLimitLawCase(LawCase lawCase) {
        return dao.selectLawCaseSelective(lawCase);
    }

    @Override
    public int deleteLawCase(String cId) {
        return dao.deleteLawCase(cId);
    }

    @Override
    public int updateLawCase(LawCase lawCase) {
        ArrayList<MapperKey> list=(ArrayList<MapperKey>) mapperDao.selectMapper(lawCase.getcId());
        for(MapperKey key:list){
            mapperDao.delete(key);
        }
        for(String uId:lawCase.getPersons()){
            MapperKey t=new MapperKey();
            t.setuId(uId);
            t.setcId(lawCase.getcId());
            mapperDao.insert(t);
        }
        return dao.updateSelective(lawCase);
    }

    @Override
    public List selectLawCaseByPerson(String uId) {
        ArrayList<LawCase> list=(ArrayList<LawCase>) dao.selectLawCaseByPerson(uId);
        return list;
    }

    @Override
    public LawCase selectLawCase(String cId) {
        LawCase lawCase=dao.selectLawCase(cId);
        return lawCase;
    }
}
