package com.mm.zhice.controller;

import com.mm.zhice.CustomerException;
import com.mm.zhice.pojo.*;
import com.mm.zhice.service.CompanyService;
import com.mm.zhice.service.RecordService;
import com.mm.zhice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 历史预测记录相关功能controller
 * @author zm
 *
 */
@RestController
@RequestMapping("/history")
public class HistoryController {
	@Autowired
	private RecordService recordService;

	@RequestMapping(value = "/page.action", method = RequestMethod.GET)
	public TableResultDTO load(Integer page, Integer rows) {
		TableResultDTO result = new TableResultDTO("9999", "unknown error", null, 0l);
		Page<RecordDO> records = null;
		try{
			records = recordService.listRecords(page,rows);
			result.setCode("0000");
			result.setMessage("加载成功");
			result.setRows(records.getContent());
			result.setTotal(records.getTotalElements());
		}catch (CustomerException e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}

}
