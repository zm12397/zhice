package com.mm.zhice.controller;

import com.mm.zhice.CustomerException;
import com.mm.zhice.pojo.KnifeDO;
import com.mm.zhice.pojo.NormalResultDTO;
import com.mm.zhice.pojo.RecordDO;
import com.mm.zhice.pojo.SysUserDO;
import com.mm.zhice.service.KnifeService;
import com.mm.zhice.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 实时预测相关功能controller
 *
 * @author zm
 */
@RestController
@RequestMapping("/realtime")
public class RealtimeController {
    @Autowired
    private KnifeService knifeService;

    @Autowired
    private RecordService recordService;

	/*@RequestMapping(value = "/list.action", method = RequestMethod.GET)
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
	}*/

    @RequestMapping(value = "/select.action", method = RequestMethod.GET)
    public NormalResultDTO predict(Long knifeID) {
        NormalResultDTO result = new NormalResultDTO("9999", "unknown error", null);
        KnifeDO knife;
        List<RecordDO> records;
        try {
            knife = knifeService.getKnife(knifeID);
            records = recordService.listRecords(knife);
            result.setCode("0000");
            result.setMessage("实时数据加载成功");
            result.setData(records);
        } catch (CustomerException e) {
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
