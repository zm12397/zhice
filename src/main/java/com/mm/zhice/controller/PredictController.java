package com.mm.zhice.controller;

import com.mm.zhice.CustomerException;
import com.mm.zhice.pojo.*;
import com.mm.zhice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 预测相关功能controller
 * @author zm
 *
 */
@RestController
@RequestMapping("/predict")
public class PredictController {
	@Autowired
	private KnifeService knifeService;

	@Autowired
	private RecordService recordService;

	@RequestMapping(value = "/init.action", method = RequestMethod.GET)
	public NormalResultDTO init() {
		NormalResultDTO result = new NormalResultDTO("9999","unknown error",null);
		try{
			List<KnifeDO> knifes = knifeService.getKnifes();
			result.setCode("0000");
			result.setMessage("初始化刀具列表成功");
			result.setData(knifes);
		}catch(CustomerException e){
			result.setMessage(e.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/predict.action", method = RequestMethod.POST)
	public NormalResultDTO predict(Long knifeID,String number, Long fileId, HttpSession session) {
		NormalResultDTO result = new NormalResultDTO("9999","unknown error",null);
		Double life;
		KnifeDO knife;
		try{
			if(knifeID == null){
				knife = new KnifeDO();
				knife.setCommitter((SysUserDO) session.getAttribute("user"));
				knife.setNumber(number);
				knifeService.addKnife(knife);
			}else{
				knife = knifeService.getKnife(knifeID);
			}
			life = recordService.predict(knife,fileId);
			result.setCode("0000");
			result.setMessage("预测成功");
			result.setData(life);
		}catch(CustomerException e){
			result.setMessage(e.getMessage());
		}
		return result;
	}
}
