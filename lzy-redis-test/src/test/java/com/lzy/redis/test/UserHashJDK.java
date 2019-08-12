/**   
 * Copyright © 2019 哈喇子. All rights reserved.
 * 
 * @Title: UserJDKtest.java 
 * @Prject: lzy-redis-test
 * @Package: com.lzy.redis.test 
 * @Description: TODO
 * @author: 哈喇子. 
 * @date: 2019年8月12日 上午8:47:28 
 * @version: V1.0   
 */

package com.lzy.redis.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lzy.redis.domain.User;
import com.lzy.redis.utils.RandomUitl;
import com.lzy.redis.utils.StringUtil;

/** 
 * @ClassName: UserJDKtest 
 * @Description: TODO
 * @author: 哈喇子.
 * @date: 2019年8月12日 上午8:47:28  
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-beans.xml")
public class UserHashJDK {

	@Resource
	private RedisTemplate<String, Serializable> redis;
	/** 
	 * @Title: jdk_hash_test 
	 * @Description:Redis的Hash类型保存十万个user随机对象到Redis，并计算耗时
	 * @return: void
	 */
	@Test
	public void jdk_hash_test() {
		List<User> users = new ArrayList<User>();
		for (int i = 1; i <= 100000; i++) {
			users.add(new User(i, StringUtil.randomChineseString()+StringUtil.randomChineseString(2), RandomUitl.random(0, 1) == 0 ? "男" : "女", 13+RandomUitl.randomString(9), RandomUitl.randomEnglishduo()+"@qq.com", ""+RandomUitl.random(18, 70)));
		}
		// 开始时间
		long start = System.currentTimeMillis();
		for (User user : users) {
			redis.opsForHash().put("user", "user_"+user.getId(), user);
		}
		// 结束时间
		long stop= System.currentTimeMillis();
		System.out.println("系列化方式 :JDK储存类型Hash保存数量:"+users.size()+"所耗时间:"+(stop-start));
		
	}
}
