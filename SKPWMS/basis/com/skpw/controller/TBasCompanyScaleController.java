package com.skpw.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.TBasCompanyScale;
import com.skpw.common.DateUtil;
import com.skpw.service.TBasCompanyScaleService;



@Controller
public class TBasCompanyScaleController {

	
	@Resource
	private TBasCompanyScaleService tBasCompanyScaleService;
	
	//分页查询
	@RequestMapping("/tBasCompanyScaleController/findByPage")
	public @ResponseBody Map<String, Object> findByPage(int page, int rows){
		//用户存储数据，转为json
		Map<String, Object> map = new HashMap<String, Object>();
		//总条数
		int total = tBasCompanyScaleService.queryCount();
		Pageable pageable = new PageRequest(page-1, rows);
		//查询出当前页面的信息
		List<TBasCompanyScale> list = tBasCompanyScaleService.findByPage(pageable);
		
		map.put("total", total);
		map.put("rows", list);
		return map;
	}
	
	//保存
	@RequestMapping("/tBasCompanyScaleController/save")
	public void save(HttpServletResponse res, TBasCompanyScale bean){
		String str = "true";
		try {
			
			try{
				String id = bean.getFcompanyScaleId();
				//判断是否为新添加的，是则设置创建人和创建时间
				if(id==null || id==""){
					bean.setFcreatorId("creator");
					bean.setFcreatTime(DateUtil.gettimestamp().toString());
				}
				//设置最后修改人和时间
				bean.setFlastEditId("editer");
				bean.setFlastEditTime(DateUtil.gettimestamp());
				tBasCompanyScaleService.save(bean);
			}catch(Exception e){
				str = "false";
				e.printStackTrace();
			}
			
			res.getWriter().print(str);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//删除
	@RequestMapping("/tBasCompanyScaleController/del")
	public @ResponseBody Map<String, Object> del(String id){
		Map<String, Object> map = new HashMap<String, Object>();
		Boolean b = true;
		try{
			tBasCompanyScaleService.del(id);
		}catch(Exception e){
			b = false;
			e.printStackTrace();
		}
		map.put("result", b);
		return map;
	}
}
