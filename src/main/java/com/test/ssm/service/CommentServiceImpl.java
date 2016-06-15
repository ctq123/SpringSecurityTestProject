package com.test.ssm.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.test.ssm.dao.CommentDao;
import com.test.ssm.entity.Comment;

@Service
public class CommentServiceImpl  implements CommentService{
	private Logger logger = Logger.getLogger(CommentServiceImpl.class);
	
	@Resource
	private CommentDao commentDao;
	
	public int insertSelective(Comment record){
		try{
			return this.commentDao.insertSelective(record);
		}catch(Exception e){
			logger.error("CommentServiceImpl insertSelective error:"+e.getMessage());
		}
		return 0;
	}

	@Override
	public List<Comment> selectAll() {
		try{
			return (List<Comment>) this.commentDao.selectAll();
		}catch(Exception e){
			logger.error("CommentServiceImpl selectAll error:"+e.getMessage());
		}
		return null;
	}
	
	public int selectMaxID() {
		try{
			return this.commentDao.selectMaxID();
		}catch(Exception e){
			logger.error("CommentServiceImpl selectMaxID error:"+e.getMessage());
		}
		return 1;
	}

}
