package com.skpw.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.skpw.bean.TBasTown;
import com.skpw.common.DateUtil;
import com.skpw.service.TBasTownService;
import com.skpw.service.TBasTownSpecs;

@Controller
public class TBasTownController {

	@Resource
	private TBasTownService tBasTownService;
	
	/**
	 * 查询disable为fasle的city
	 * @return
	 */
	@RequestMapping("/tBasTownController/getList")
	public @ResponseBody List<TBasTown> getList(){
		//设置条件
		TBasTown tbc = new TBasTown();
		tbc.setFisDisable(false);
		Specification<TBasTown> spf = TBasTownSpecs.getOneForSearch(tbc);
		List<TBasTown> list = tBasTownService.getList(spf);
		return list;
	}
	/**
	 * 分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/tBasTownController/findByPage")
	public @ResponseBody Map<String, Object> findByPage(int page, int rows){
		//容器
		Map<String, Object> map = new HashMap<String, Object>();
		//查询数据总条数
		int total = tBasTownService.queryCount();
		//分页条件
		Pageable pageable = new PageRequest(page-1, rows);
		List<TBasTown> list = tBasTownService.findByPage(pageable);
		map.put("total", total);
		map.put("rows", list);
		return map;
	}
	
	/**
	 * 保存
	 * @param res
	 * @param tee
	 */
	@RequestMapping("/tBasTownController/save")
	public void save(HttpServletResponse res, TBasTown tee){
		String str = "true";
		try {
			try{
				//设置创建人和创建时间
				if(tee.getFcreatorId() !=null && tee.getFcreatorId() != ""){
					tee.setFcreatorId("");
					tee.setFcreatTime(DateUtil.gettimestamp());
				}
				//设置最后修改人和修改时间
				tee.setFlastEditId("");
				tee.setFlastEditTime(DateUtil.gettimestamp());
				
				tBasTownService.save(tee);
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
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/tBasTownController/del")
	public @ResponseBody Map<String, Object> del(String id){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			tBasTownService.del(id);
			map.put("result", true);
		}catch(Exception e){
			map.put("result", false);
			e.printStackTrace();
		}
		return map;
	}
}
