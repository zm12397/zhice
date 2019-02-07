package com.mm.zhice.service;

import com.mm.zhice.pojo.CompanyDO;
import com.mm.zhice.pojo.SysUserDO;

public interface UserService {
    SysUserDO getUser(String username,String password);

    SysUserDO getUser(String username);

    SysUserDO addUser(CompanyDO company, String username, String password);
}
