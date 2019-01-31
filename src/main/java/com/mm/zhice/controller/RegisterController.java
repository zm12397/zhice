package com.mm.zhice.controller;

import com.mm.zhice.CustomerException;
import com.mm.zhice.pojo.NormalResultDTO;
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

	@RequestMapping(value = "/validate.action", method = RequestMethod.POST)
	public NormalResultDTO validate(String username) {
		
		NormalResultDTO result = new NormalResultDTO("9999","unknown error",null);
		boolean isUsed = false;
		try{

			result.setCode("0000");
		}catch(CustomerException e){
			result.setMessage(e.getMessage());
			return result;
		}
		if (isUsed) {
			result.setMessage("用户名可以使用");
			return result;
		} else {
			result.setMessage("用户名已经存在");
		}
		return result;
	}

}
