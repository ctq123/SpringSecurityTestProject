package com.test.ssm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.ssm.entity.Comment;
import com.test.ssm.service.CommentService;

@Controller
@RequestMapping("/comment")

public class CommentController {

	@Resource
	private CommentService commentService;
	
	@ResponseBody
    @RequestMapping("/getCommentList")
    public List<Comment> getCommentList(HttpServletResponse response,ModelMap modelMap) throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");//øÁ”Ú∑√Œ 
		List<Comment> product_list = new ArrayList<Comment>();
		product_list = commentService.selectAll();
	    return product_list;
			
	}
	
	@ResponseBody
	@RequestMapping(value = "/replyComment", method={RequestMethod.POST})  
    public List<Comment> replyComment(@RequestBody Comment com, HttpServletRequest request, HttpServletResponse response,ModelMap modelMap) throws IOException {
		if(com != null){
			boolean isValid = true;
			if("".equals(com.getAuthor()) || com.getAuthor()==null){
				isValid=false;
			}
			if("".equals(com.getContent()) || com.getContent()==null){
				isValid=false;
			}
			if(isValid){
				commentService.insertSelective(com);
			}
		}
		//response.addHeader("Access-Control-Allow-Origin", "*");//øÁ”Ú∑√Œ 
		List<Comment> product_list = new ArrayList<Comment>();
		product_list = commentService.selectAll();
	    return product_list;
	}
}
