package com.mm.zhice.dao;


import com.mm.zhice.pojo.CompanyDO;

public interface CompanyDao  extends BaseDao<CompanyDO,Long>{
    CompanyDO findByNumber(String number);
}
