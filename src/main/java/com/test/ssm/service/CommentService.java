package com.test.ssm.service;

import java.util.List;

import com.test.ssm.entity.Comment;

public interface CommentService {
	public int insertSelective(Comment record);
	public List<Comment> selectAll();
}
