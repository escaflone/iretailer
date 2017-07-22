package com.iretailer;

import com.iretailer.dto.*;
import com.iretailer.service.BaseService;
import com.iretailer.service.MetaDataService;
import com.iretailer.service.PageWidgetService;
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
	@Autowired
	private MetaDataService metaDataService;
	@Autowired
	private PageWidgetService pageWidgetService;



	@Test
	public void contextLoads() {}

	@Test
	public void testBaseService(){
		DataQueryParam dqp = new DataQueryParam();
		dqp.getDataFields().add("count_in");
		dqp.getDataFields().add("count_out");
//		dqp.getDataFields().add("count_passby");
//		dqp.getDataFields().add("count_trades");
//		dqp.getDataFields().add("count_sales");
//		dqp.getDataFields().add("count_goods");
//		dqp.getDataFields().add("acv");
//		dqp.getDataFields().add("upt");
		dqp.setStartTime(1498867200000l);//2016-11-26
		dqp.setEndTime(1501372800000l);//2016-12-01
//		dqp.setStartTime(1477929600000l);
//		dqp.setEndTime(1477929600000l);
		dqp.setPeriod("d");
//		dqp.setSplit(0);
//		dqp.getGroupBy().setPeriod("10");
//		dqp.getSortBy().put("enter",1);
		dqp.setSiteIdList(Arrays.asList(new Integer[] {20,3}));
//		dqp.setLocation("quare_name");
//		dqp.setSiteType("业态");
		baseService.query(dqp);
	}
//
//	@Test
//	public void testUserService_query(){
//		List<User> users = userService.query();
//		System.out.println(users.size());
//	}
//	@Test
//	public void testUserService_create(){
//		User user = new User();
//		user.setRoleId(1l);
//		user.setUserName("clat");
//		user.setPassWord("4513565");
//		user.setEmailAddress("seafjkdlaj@123.com");
//		user.setPhoneNumber("416546351");
//		user.setCompany("易思密");
//		user.setDescription("123");
//		user.setLocked(0);
//		int id = userService.create(user);
//		System.out.println(user.getUserId());
//	}
//	@Test
//	public void testUserService_queryById(){
//		User user = userService.queryById(8);
//		System.out.println(user);
//	}
//	@Test
//	public void testMetaService(){
//		List<Site> list = metaDataService.query();
//		System.out.println(list.size());
//	}
//	@Test
//	public void testMetaService_queryLocation(){
//		List<Location> list = metaDataService.queryLocation();
//		System.out.println(list.size());
//	}
//
//	@Test
//	public void voidTestPageWidget_Create(){
//		PageWidget pageWidget = new PageWidget();
//		pageWidget.setChat("chat1");pageWidget.setCode("code1");pageWidget.setName("name1");
//		pageWidget.setQuery("query1");pageWidget.setSizex(1);pageWidget.setSizey(1);
//		pageWidget.setRank(1);
//		pageWidgetService.create(pageWidget);
//	}
//
//	@Test
//	public void voidTestPageWidget_update(){
//		PageWidget pageWidget = new PageWidget();
//		pageWidget.setId(9l);
//		pageWidget.setRank(2);
//		pageWidgetService.update(pageWidget);
//	}
//	@Test
//	public void voidTestPageWidget_query(){
//		List<PageWidget> pageWidgets = pageWidgetService.query();
//		System.out.println(pageWidgets);
//	}
//	@Test
//	public void voidTestPageWidget_delete(){
//		PageWidget pageWidget = new PageWidget();
//		pageWidget.setId(9l);
//		pageWidgetService.delete(pageWidget);
//	}


}