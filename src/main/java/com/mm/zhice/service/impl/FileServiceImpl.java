package com.mm.zhice.service.impl;

import com.mm.zhice.CustomerException;
import com.mm.zhice.dao.DataFileDao;
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
	
	public static final String USER_IMG_UPLOAD_PATH = "/static/img/user/";
	public static final String USER_IMG_NM_PREFIX = "user_img_";
	public static final String CERTIFICATE_IMG_UPLOAD_PATH = "/static/img/certificate/";
	public static final String CERTIFICATE_IMG_NM_PREFIX = "certificate_img_";
	// 有效的文件后缀
	private static List<String> validedFileNameList = Arrays.asList("jpg", "gif", "bmp", "png", "jpeg", "doc", "docx",
			"xls", "xlsx", "ppt", "pptx", "swf", "rar", "zip", "pdf", "txt");

	// 有效的图片文件后缀
	private static List<String> validedImgFileNameList = Arrays.asList("jpg", "gif", "bmp", "png", "jpeg");

	// 是否为有效的图片文件
	public boolean validateImgType(String filename) {
		// filename = file.getOriginalFilename()
		String type = filename.substring(filename.indexOf(".") + 1).toLowerCase(); // 获取文件后缀
		if (!validedImgFileNameList.contains(type)) {
			return false;
		}
		return true;
	}

	@Override
	public File  saveFile(String filename, String path,int type) {
		// TODO Auto-generated method stub
		String prefix = USER_IMG_NM_PREFIX;
		String relativePath = USER_IMG_UPLOAD_PATH;
		if(type == 1){
			prefix = CERTIFICATE_IMG_NM_PREFIX;
			relativePath = CERTIFICATE_IMG_UPLOAD_PATH;
		}
		String saveName = prefix + TimeUtils.getNow() + "." +
				filename.substring(filename.indexOf(".") + 1).toLowerCase();
		log.info(path + relativePath + saveName);
		File file = new File(path + relativePath + saveName);
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			log.error("文件资源本地创建失败：" + e.getMessage());
			throw new CustomerException("文件资源本地创建失败");
		}
		return file;
		
	}
}
