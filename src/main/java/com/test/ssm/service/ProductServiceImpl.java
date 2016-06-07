package com.test.ssm.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.test.ssm.dao.ProductDao;
import com.test.ssm.entity.Product;

@Service
public class ProductServiceImpl  implements ProductService{
	private Logger logger = Logger.getLogger(ProductServiceImpl.class);
	
	@Resource
	private ProductDao productDao;

	@Override
	public List<Product> selectAll() {
		try{
			return this.productDao.selectAll();
		}catch(Exception e){
			logger.error("ProductServiceImpl selectAll error:"+e.getMessage());
		}
		return null;
	}

}
