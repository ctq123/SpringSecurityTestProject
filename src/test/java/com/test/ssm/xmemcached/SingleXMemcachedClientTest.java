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
		//ָ����̨��xmemcached������
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses("10.45.9.119:11211 10.45.9.189:11211"),new int[]{1,1});
		//�������ӳش�С�������ӿͻ�����
		builder.setConnectionPoolSize(50);
		
		//�����ѻ�����
		builder.setFailureMode(true);
		
		//ʹ�ö������ļ�
		builder.setCommandFactory(new BinaryCommandFactory());
		
		MemcachedClient memcachedClient = null;
		try{
			memcachedClient = builder.build();
			
			//���úͻ�ȡ
			memcachedClient.set("namekey1",6000,"jack1");
			System.out.println("namekey1="+memcachedClient.get("namekey1"));
			
			//ʵ��һ���Թ�ϣ�������������ݽ��ֲ������namekey1��ͬ�Ľڵ���
			memcachedClient.set("namekey2", 6000, "jack2");
			memcachedClient.set("namekey3", 6000, "jack3");
			memcachedClient.set("namekey4", 6000, "jack4");
			memcachedClient.set("namekey5", 6000, "jack5");
			memcachedClient.set("namekey6", 6000, "jack6");
			memcachedClient.set("namekey7", 6000, "jack7");
			memcachedClient.set("namekey8", 6000, "jack8");
			memcachedClient.set("namekey9", 6000, "jack9");
			memcachedClient.set("namekey10", 6000, "jack10");
			//�滻
			memcachedClient.replace("namekey1",6000,"jack11");
			System.out.println("namekey1="+memcachedClient.get("namekey1"));
			
			//ɾ��
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
