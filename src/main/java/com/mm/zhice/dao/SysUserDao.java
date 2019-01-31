package com.mm.zhice.dao;

import com.mm.zhice.pojo.SysUserDO;

import java.util.List;

public interface SysUserDao extends BaseDao<SysUserDO,Integer>{
	List<SysUserDO> findByUsername(String username);
}
