package com.mm.zhice.dao;

import com.mm.zhice.pojo.SysRoleDO;

public interface SysRoleDao  extends BaseDao<SysRoleDO,Long>{

	SysRoleDO findByRole(String string);

}
