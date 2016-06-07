package com.test.ssm.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.test.ssm.dao.RoleDao;
import com.test.ssm.dao.UserDao;
import com.test.ssm.entity.Role;
import com.test.ssm.entity.User;

@Service
public class UserServiceImpl implements UserService ,UserDetailsService{

	private Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private RoleDao roleDao;
	
	public User getUserById(int id){
		try{
			return this.userDao.selectByPrimaryKey(id);
		}catch(Exception e){
			logger.error("UserServiceImpl getUserById error:"+e.getMessage());
		}
		return null;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException{
		User user = null;
		try{
			user = userDao.selectByUsername(username);
			if(user==null || user.getId()==null){throw new Exception("username="+username+" not found in db");}
			//获取角色权限
			List<Role> roleList = roleDao.selectListByUserId(user.getId());
			//设置user实体类角色权限
			user.setRoleList(roleList);
			//设置springsecurity角色权限
			user.getAuthorities();
			
//			SecurityContextHolder.getContext().getAuthentication().
			
			//设置认证用户UserDetails
			UserDetails userdetails = new org.springframework.security.core.userdetails.User(
					user.getUsername(), user.getPassword(),true, true, true, true, user.getAuthorities());
			
			return userdetails;
		}catch(Exception e){
			logger.error("UserServiceImpl loadUserByUsername error. username =["+username+"] error:"+e.getMessage());
			throw new UsernameNotFoundException("Error in loadUserByUsername username ="+username);
		}
	}
}
