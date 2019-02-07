package com.mm.zhice.service;

import com.mm.zhice.pojo.KnifeDO;

import java.util.List;

public interface KnifeService {
    List<KnifeDO> getKnifes();

    void addKnife(KnifeDO knife);

    KnifeDO getKnife(Long knifeID);
}
