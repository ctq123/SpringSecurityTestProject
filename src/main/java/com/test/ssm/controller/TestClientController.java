package com.test.ssm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.ssm.entity.Product;
import com.test.ssm.service.ProductService;

@Controller
@RequestMapping("/test")

public class TestClientController {

	@Resource
	private ProductService productService;
	
	@RequestMapping("/getData")
	public String toIndex(HttpServletRequest request,ModelMap modelmap){
		modelmap.put("data", "success to get data");
		return "testClient";
	}
	
	@ResponseBody
    @RequestMapping("/getList")
    public List<Product> getList(HttpServletResponse response,ModelMap modelMap) throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");//øÁ”Ú∑√Œ 
//		response.setContentType("application/json");  
//	    response.setCharacterEncoding("UTF-8");
//		response.setContentType("application/json; charset=UTF-8");
		List<Product> product_list = new ArrayList<Product>();
		product_list = productService.selectAll();
//		response.getWriter().print(product_list);
	    return product_list;
//		String json="";
//		List<String> result = new ArrayList<String>();
//		json = "{'category':'Sporting Goods', 'price':'$49.99', 'stocked':true, 'name':'Football'}";
//		result.add(json);
//		json = "{'category':'Sporting Goods', 'price':'$9.99', 'stocked':true, 'name':'Baseball'}";
//		result.add(json);
//		response.getWriter().print(result);
//		return result;
			
	}
}
