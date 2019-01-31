package com.mm.zhice.controller;

import com.mm.zhice.pojo.NormalResultDTO;
import com.mm.zhice.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录相关功能controller
 * 
 * @author zm
 *
 */
@RestController
@RequestMapping("/login")
public class LoginController {
	private Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	/**
	 * 功能：登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login.action", method = RequestMethod.POST)
	public NormalResultDTO login(String username, String password) {
		NormalResultDTO result = new NormalResultDTO("9999", "unknown error", null);
		/*try {
			UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password.toCharArray());
			// usernamePasswordToken.setRememberMe(true);// 记住我
			try {
				SecurityUtils.getSubject().login(usernamePasswordToken);
				result.setCode("0000");
				result.setMessage("登录成功");
			} catch (UnknownAccountException uae) {
				log.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
				result.setMessage("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
			} catch (IncorrectCredentialsException ice) {
				log.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证：" + ice.getMessage());
				result.setMessage("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
			} catch (LockedAccountException lae) {
				log.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
				result.setMessage("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
			} catch (ExcessiveAttemptsException eae) {
				log.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
				result.setMessage("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
			} catch(DisabledAccountException dae){
				log.info("对用户[" + username + "]进行登录验证..验证未通过,该账户不是激活状态");
				result.setMessage("该用户已被注销");
			}catch (AuthenticationException ae) {
				// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
				log.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
				result.setMessage("用户名或密码不正确");
			}

		} catch (Exception e) {
			log.info("登录异常" + e.getMessage());
			result.setMessage("登录异常");
		}*/
		return result;
	}
}
