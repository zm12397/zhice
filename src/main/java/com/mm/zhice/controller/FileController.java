package com.mm.zhice.controller;

import com.mm.zhice.CustomerException;
import com.mm.zhice.pojo.DataFileDO;
import com.mm.zhice.pojo.KnifeDO;
import com.mm.zhice.pojo.NormalResultDTO;
import com.mm.zhice.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 文件操作相关controller
 * @author zm
 *
 */
@RestController
@RequestMapping("/file")
public class FileController {
	@Autowired
	private FileService fileService;

	private Logger log = LoggerFactory.getLogger(FileController.class);

	@RequestMapping(value = "/data.action", method = RequestMethod.POST)
	public NormalResultDTO datafile(MultipartFile file, HttpServletRequest request) {
		NormalResultDTO result = new NormalResultDTO("9999","unknown error",null);
		final String filename = file.getOriginalFilename();
		if(!fileService.validate(filename)){
			result.setMessage("文件类型不正确");
			return result;
		}
		// 获取项目所在绝对路径
		String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
		File localFile = null;
		try{
			localFile = fileService.saveFile(filename,path);
		}catch(CustomerException e){
			result.setMessage(e.getMessage());
			return result;
		}
		try {
			file.transferTo(localFile);
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			log.error(e1.getMessage());
			result.setMessage("文件状态异常");
			return result;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			log.error(e1.getMessage());
			result.setMessage("文件读写异常");
			return result;
		}
		DataFileDO dataFile = new DataFileDO();
		dataFile.setPath(localFile.getName());
		try{
			fileService.addDataFile(dataFile);
		}catch(CustomerException e){
			result.setMessage(e.getMessage());
			return result;
		}
		if(dataFile.getId() != null){
			result.setCode("0000");
			result.setMessage("文件上传成功");
			result.setData(dataFile.getId());
		}else{
			result.setMessage("文件上传失败");
		}
		return result;
	}
}
