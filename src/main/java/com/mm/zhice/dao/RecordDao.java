package com.mm.zhice.dao;


import com.mm.zhice.pojo.KnifeDO;
import com.mm.zhice.pojo.RecordDO;

import java.util.List;

public interface RecordDao extends BaseDao<RecordDO,Long>{
    List<RecordDO> findByKnifeOrderByGmtCreate(KnifeDO knife);
}
