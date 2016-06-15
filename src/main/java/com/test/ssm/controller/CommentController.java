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
import org.springframework.web.bind.annotation.RequestParam;
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
		response.addHeader("Access-Control-Allow-Origin", "http://localhost:8001");//øÁ”Ú∑√Œ 
		List<Comment> product_list = new ArrayList<Comment>();
		product_list = commentService.selectAll();
	    return product_list;
			
	}
	
	@ResponseBody
	@RequestMapping("/replyComment")  
    public List<Comment> replyComment(@RequestParam(value="author",required = true) String author,@RequestParam(value="content",required = true) String content,HttpServletRequest request, HttpServletResponse response,ModelMap modelMap) throws IOException {
		Comment com = new Comment();
		int id=commentService.selectMaxID();
		com.setId(id+1);
		com.setAuthor(author);
		com.setContent(content);
		commentService.insertSelective(com);	
		response.addHeader("Access-Control-Allow-Origin", "*");//øÁ”Ú∑√Œ 
		List<Comment> product_list = new ArrayList<Comment>();
		product_list = commentService.selectAll();
	    return product_list;
	}
}
