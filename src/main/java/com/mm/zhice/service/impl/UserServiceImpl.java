package com.mm.zhice.service.impl;

import com.mm.zhice.dao.SysUserDao;
import com.mm.zhice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private SysUserDao userDao;
}
