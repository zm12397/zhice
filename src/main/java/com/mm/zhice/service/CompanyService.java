package com.mm.zhice.service;

import com.mm.zhice.pojo.CompanyDO;

public interface CompanyService {
    CompanyDO getCompany(String number);

    CompanyDO addCompany(String name, String number);
}
