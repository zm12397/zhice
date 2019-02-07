package com.mm.zhice.controller;

import com.mm.zhice.CustomerException;
import com.mm.zhice.pojo.CompanyDO;
import com.mm.zhice.pojo.NormalResultDTO;
import com.mm.zhice.pojo.SysUserDO;
import com.mm.zhice.service.CompanyService;
import com.mm.zhice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册相关功能controller
 * @author zm
 *
 */
@RestController
@RequestMapping("/register")
public class RegisterController {
	@Autowired
	private CompanyService companyService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/validate/user.action", method = RequestMethod.POST)
	public NormalResultDTO validate(String username) {
		NormalResultDTO result = new NormalResultDTO("9999","unknown error",null);
		try{
			if(userService.getUser(username) == null){
				result.setMessage("用户名未被注册，可以使用");
			}else{
				result.setMessage("用户名已被注册");
			}
			result.setCode("0000");
		}catch(CustomerException e){
			result.setMessage(e.getMessage());
			return result;
		}
		return result;
	}

	@RequestMapping(value = "/validate/company.action", method = RequestMethod.POST)
	public NormalResultDTO validateCompany(String number) {
		NormalResultDTO result = new NormalResultDTO("9999","unknown error",null);
		try{
			if(companyService.getCompany(number) == null){
				result.setMessage("编号未被注册，可以使用");
			}else{
				result.setMessage("编号已被注册");
			}
			result.setCode("0000");
		}catch(CustomerException e){
			result.setMessage(e.getMessage());
			return result;
		}
		return result;
	}

	@RequestMapping(value = "/register.action", method = RequestMethod.POST)
	public NormalResultDTO validateCompany(String name,String number,String username,String password) {
		NormalResultDTO result = new NormalResultDTO("9999","unknown error",null);
		CompanyDO company;
		SysUserDO user;
		try{
			company = companyService.addCompany(name,number);
			if(company.getId() == null){
				result.setMessage("公司注册失败，未知错误");
			}else{
				user = userService.addUser(company,username,password);
				if(user.getId() == null){
					result.setMessage("用户注册失败，未知错误");
				}else{
					result.setMessage("注册成功");
					result.setCode("0000");
				}
			}
		}catch(CustomerException e){
			result.setMessage(e.getMessage());
		}
		return result;
	}

}
