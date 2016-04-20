package com.test.ssm.xmemcached;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

public class SingleXMemcachedClientTest {

	public static void main(String args[]){
		//指定两台跑xmemcached服务器
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses("10.45.9.119:11211 10.45.9.189:11211"),new int[]{1,1});
		//设置连接池大小，即连接客户端数
		builder.setConnectionPoolSize(50);
		
		//设置脱机报警
		builder.setFailureMode(true);
		
		//使用二进制文件
		builder.setCommandFactory(new BinaryCommandFactory());
		
		MemcachedClient memcachedClient = null;
		try{
			memcachedClient = builder.build();
			
			//设置和获取
			memcachedClient.set("namekey1",6000,"jack1");
			System.out.println("namekey1="+memcachedClient.get("namekey1"));
			
			//实验一次性哈希，以下两条数据将分步到与存namekey1不同的节点上
			memcachedClient.set("namekey2", 6000, "jack2");
			memcachedClient.set("namekey3", 6000, "jack3");
			memcachedClient.set("namekey4", 6000, "jack4");
			memcachedClient.set("namekey5", 6000, "jack5");
			memcachedClient.set("namekey6", 6000, "jack6");
			memcachedClient.set("namekey7", 6000, "jack7");
			memcachedClient.set("namekey8", 6000, "jack8");
			memcachedClient.set("namekey9", 6000, "jack9");
			memcachedClient.set("namekey10", 6000, "jack10");
			//替换
			memcachedClient.replace("namekey1",6000,"jack11");
			System.out.println("namekey1="+memcachedClient.get("namekey1"));
			
			//删除
			memcachedClient.delete("namekey1");
			memcachedClient.delete("namekey2");
			memcachedClient.delete("namekey3");
			memcachedClient.delete("namekey4");
			memcachedClient.delete("namekey5");
			memcachedClient.delete("namekey6");
			memcachedClient.delete("namekey7");
			memcachedClient.delete("namekey8");
			memcachedClient.delete("namekey9");
			memcachedClient.delete("namekey10");
			
		}catch(TimeoutException e){
			e.printStackTrace();
		}catch(InterruptedException e){
			e.printStackTrace();
		}catch(MemcachedException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(memcachedClient != null){
				try {
					memcachedClient.shutdown();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
