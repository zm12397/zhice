package com.mm.zhice.service.impl;

import com.mm.zhice.CustomerException;
import com.mm.zhice.dao.CompanyDao;
import com.mm.zhice.pojo.CompanyDO;
import com.mm.zhice.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
	Logger log = LoggerFactory.getLogger(CompanyService.class);
	@Autowired
	private CompanyDao companyDao;


	@Override
	public CompanyDO getCompany(String number) {
		CompanyDO company = null;
		try {
			company = companyDao.findByNumber(number);
			return company;
		}catch (Exception e){
			log.error("查询失败" + e.getMessage());
			throw new CustomerException("查询失败");
		}
	}

	@Override
	public CompanyDO addCompany(String name, String number) {
		CompanyDO company = new CompanyDO();
		try {
			company.setName(name);
			company.setNumber(number);
			company.setGmtCreate(new Date());
			company.setGmtModified(new Date());
			company.setState((short) 1);
			companyDao.save(company);
			return company;
		}catch (Exception e){
			log.error("持久化公司失败" + e.getMessage());
			throw new CustomerException("公司注册失败");
		}
	}
}
