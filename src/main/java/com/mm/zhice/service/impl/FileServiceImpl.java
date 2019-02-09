package com.mm.zhice.service.impl;

import com.mm.zhice.CustomerException;
import com.mm.zhice.dao.DataFileDao;
import com.mm.zhice.pojo.DataFileDO;
import com.mm.zhice.service.FileService;
import com.mm.zhice.util.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class FileServiceImpl implements FileService {
	private Logger log = LoggerFactory.getLogger(FileService.class);
	@Autowired
	private DataFileDao dataFileDao;
	
	private static final String DEFAULT_DATA_UPLOAD_PATH = "/static/file/data/batch/";
	private static final String DEFAULT_SPLIT = "_";
	/*private static final String USER_IMG_NM_PREFIX = "user_img_";
	public static final String CERTIFICATE_IMG_UPLOAD_PATH = "/static/img/certificate/";
	public static final String CERTIFICATE_IMG_NM_PREFIX = "certificate_img_";*/
	// 有效的文件后缀
	private static List<String> validedFileNameList = Arrays.asList("zip", "csv");

	// 是否为有效的图片文件
	@Override
	public boolean validate(String filename) {
		// filename = file.getOriginalFilename()
		String type = filename.substring(filename.indexOf(".") + 1).toLowerCase(); // 获取文件后缀
		if (!validedFileNameList.contains(type)) {
			return false;
		}
		return true;
	}

	@Override
	public File saveFile(String filename, String path) {
		if(filename == null){
			throw new CustomerException("上传文件为空");
		}
		String suffix = filename.substring(filename.indexOf(".") + 1).toLowerCase();
		String dataFileName = generateDataFileName("knife",suffix,null);
		File file = new File(path + DEFAULT_DATA_UPLOAD_PATH + dataFileName);
		try{
			file.createNewFile();
		}catch (IOException e){
			log.error("创建本地文件资源失败：" + e.getMessage());
			throw new CustomerException("创建本地文件资源失败");
		}
		return file;
	}

	@Override
	public void addDataFile(DataFileDO dataFile) {
		try {
			dataFileDao.save(dataFile);
		}catch (Exception e){
			log.error("数据对象持久化异常" +e.getMessage());
			throw new CustomerException("数据对象持久化异常");
		}
	}

	@Override
	public DataFileDO getDataFile(Long fileId) {
		DataFileDO dataFile = null;
		try {
			dataFile = dataFileDao.findOne(fileId);
			return dataFile;
		}catch (Exception e) {
			log.error("数据对象查询失败" + e.getMessage());
			throw new CustomerException("数据对象查询失败");
		}
	}

	@Override
	public void update(DataFileDO dataFile) {
		try {
			dataFileDao.save(dataFile);
		}catch (Exception e) {
			log.error("数据对象更新失败" + e.getMessage());
			throw new CustomerException("数据对象更新失败");
		}
	}

	private String generateDataFileName(String prefix,String suffix,String split){
		String splitStr = split != null ? split : DEFAULT_SPLIT;
		StringBuilder stringBuilder = new StringBuilder();
		if(prefix != null){
			stringBuilder.append(prefix);
		}
		stringBuilder.append(splitStr).append(TimeUtils.getNow());
		if(suffix != null){
			stringBuilder.append(".").append(suffix);
		}
		return stringBuilder.toString();
	}
}
