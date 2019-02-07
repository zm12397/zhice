package com.mm.zhice.service.impl;

import com.mm.zhice.CustomerException;
import com.mm.zhice.dao.SysUserDao;
import com.mm.zhice.pojo.CompanyDO;
import com.mm.zhice.pojo.SysUserDO;
import com.mm.zhice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private SysUserDao userDao;


    @Override
    public SysUserDO getUser(String username, String password) {
        SysUserDO user = null;
        try {
            user = getUser(username);
            if (user == null) {
                log.info("用户名不存在");
                throw new CustomerException("用户名不存在");
            } else {
                if (user.getPassword().equals(password)) {
                    log.info("登录成功");
                    return user;
                }
                log.info("密码不正确");
                throw new CustomerException("密码不正确");
            }
        } catch (Exception e) {
            if(e instanceof  CustomerException){
                throw e;
            }
            log.error("登录失败" + e.getMessage());
            throw new CustomerException("登录失败");
        }
    }

    @Override
    public SysUserDO getUser(String username) {
        SysUserDO user = null;
        try {
            user = userDao.findByUsername(username);
            return user;
        }catch (CustomerException e){
            throw e;
        }catch (Exception e) {
            log.error("查询失败" + e.getMessage());
            throw new CustomerException("查询失败");
        }
    }

    @Override
    public SysUserDO addUser(CompanyDO company, String username, String password) {
        SysUserDO user = new SysUserDO();
        try {
            user.setCompany(company);
            user.setUsername(username);
            user.setPassword(password);
            /*user.setGmtCreate(new Date());
            user.setGmtModified(new Date());*/
            user.setState((short) 1);
            userDao.save(user);
            return user;
        }catch (Exception e){
            log.error("持久化用户失败" + e.getMessage());
            throw new CustomerException("用户注册失败");
        }
    }
}
