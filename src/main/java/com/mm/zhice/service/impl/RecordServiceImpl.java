package com.mm.zhice.service.impl;

import com.mm.zhice.CustomerException;
import com.mm.zhice.dao.RecordDao;
import com.mm.zhice.pojo.CompanyDO;
import com.mm.zhice.pojo.DataFileDO;
import com.mm.zhice.pojo.KnifeDO;
import com.mm.zhice.pojo.RecordDO;
import com.mm.zhice.service.CompanyService;
import com.mm.zhice.service.FileService;
import com.mm.zhice.service.KnifeService;
import com.mm.zhice.service.RecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class RecordServiceImpl implements RecordService {
	Logger log = LoggerFactory.getLogger(CompanyService.class);
	@Autowired
	private RecordDao recordDao;
	@Autowired
	private FileService fileService;

	@Override
	public Double predict(KnifeDO knife, Long fileId) {
		Double life = 0.0;
		DataFileDO dataFile;
		try {
			dataFile = fileService.getDataFile(fileId);
			dataFile.setKnife(knife);
			life = predict(dataFile);
			RecordDO record = new RecordDO();
			record.setDataFile(dataFile);
			record.setCommitter(knife.getCommitter());
			record.setFinalLife(life);
			recordDao.save(record);
			dataFile.setRecord(record);
			fileService.update(dataFile);
			return life;
		}catch (Exception e){
			log.error("预测失败" + e.getMessage());
			throw new CustomerException("预测失败");
		}
	}

	@Override
	public Page<RecordDO> listRecords(Integer page, Integer rows) {
		Page<RecordDO> records;
		Sort sort = new Sort(Sort.Direction.DESC,"gmt_create");
		Pageable pageable = new PageRequest(page, rows, sort);
		try {
			records = recordDao.findAll(pageable);
		} catch (Exception e) {
			log.error("预测记录分页查询失败：" + e.getMessage());
			throw new CustomerException("预测记录分页查询失败");
		}
		return records;
	}

	@Override
	public List<RecordDO> listRecords(KnifeDO knife) {
		return knife.getRecords();
	}

	private Double predict(DataFileDO dataFile){
//		Double life = 0.0;
		Double life = (double)(new Random().nextInt(100));
		String filePath = dataFile.getPath();
		return life;
	}
}
