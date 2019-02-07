package com.mm.zhice.service.impl;

import com.mm.zhice.CustomerException;
import com.mm.zhice.dao.KnifeDao;
import com.mm.zhice.pojo.KnifeDO;
import com.mm.zhice.service.KnifeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class KnifeServiceImpl implements KnifeService {
    Logger log = LoggerFactory.getLogger(KnifeService.class);
    @Autowired
    private KnifeDao knifeDao;

    @Override
    public List<KnifeDO> getKnifes() {
        List<KnifeDO> knifes;
        try {
            knifes = knifeDao.findAll();
            return knifes;
        }catch (Exception e){
            log.error("查询失败" + e.getMessage());
            throw new CustomerException("查询失败");
        }
    }

    @Override
    public void addKnife(KnifeDO knife) {
        try {
            knifeDao.save(knife);
        }catch (Exception e){
            log.error("刀具持久化失败" + e.getMessage());
            throw new CustomerException("刀具持久化失败");
        }
    }

    @Override
    public KnifeDO getKnife(Long knifeID) {
        KnifeDO knife;
        try {
            knife = knifeDao.findOne(knifeID);
            return knife;
        }catch (Exception e){
            log.error("刀具持久化失败" + e.getMessage());
            throw new CustomerException("刀具持久化失败");
        }
    }
}
