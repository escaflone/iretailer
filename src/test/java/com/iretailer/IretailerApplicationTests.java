package com.iretailer;

import com.iretailer.dto.DataQueryParam;
import com.iretailer.dto.User;
import com.iretailer.service.BaseService;
import com.iretailer.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IretailerApplicationTests {

	@Autowired
	BaseService baseService;
	@Autowired
	UserService userService;

//	@Test
//	public void contextLoads() {}

	@Test
	public void testBaseService(){
		DataQueryParam dqp = new DataQueryParam();
		dqp.getDataFields().add("count_in");
		dqp.getDataFields().add("count_out");
		dqp.getDataFields().add("count_passby");
		dqp.getDataFields().add("count_trades");
		dqp.getDataFields().add("count_sales");
		dqp.getDataFields().add("count_goods");
		dqp.getDataFields().add("acv");
		dqp.getDataFields().add("upt");
		dqp.setStartTime(1479600000000l);//2016-11-26
		dqp.setEndTime(1480550400000l);//2016-12-01
//		dqp.getGroupBy().setPeriod("10");
//		dqp.getSortBy().put("enter",1);
		dqp.setSiteIdList(Arrays.asList(new Integer[] {35,36,37}));
//		dqp.setLocation("quare_name");
//		dqp.setSiteType("业态");
		baseService.query(dqp);
	}

	@Test
	public void testUserService_query(){
		List<User> users = userService.query();
		System.out.println(users.size());
	}
	@Test
	public void testUserService_create(){
		User user = new User();
		user.setRoleId(1l);
		user.setUserName("clat");
		user.setPassWord("4513565");
		user.setEmailAddress("seafjkdlaj@123.com");
		user.setPhoneNumber("416546351");
		user.setCompany("易思密");
		user.setDescription("123");
		user.setLocked(0);
		int id = userService.create(user);
		System.out.println(user.getUserId());
	}
	@Test
	public void testUserService_queryById(){
		User user = userService.queryById(8);
		System.out.println(user);
	}
}