package com.mm.zhice.service;

import com.mm.zhice.pojo.KnifeDO;
import com.mm.zhice.pojo.RecordDO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RecordService {
    Double predict(KnifeDO knife, Long fileId);

    Page<RecordDO> listRecords(Integer page, Integer rows);

    List<RecordDO> listRecords(KnifeDO knife);
}
