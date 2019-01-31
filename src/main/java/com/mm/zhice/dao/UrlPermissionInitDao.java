package com.mm.zhice.dao;


import com.mm.zhice.pojo.UrlPermissionInitDO;

import java.util.List;

public interface UrlPermissionInitDao  extends BaseDao<UrlPermissionInitDO,Integer>{

	List<UrlPermissionInitDO> findByOrderBySeq();

}
