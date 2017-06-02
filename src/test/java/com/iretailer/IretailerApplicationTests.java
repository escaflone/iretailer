package com.iretailer;

import com.iretailer.dto.DataQueryParam;
import com.iretailer.service.BaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IretailerApplicationTests {

	@Autowired
	BaseService baseService;

//	@Test
//	public void contextLoads() {}

	@Test
	public void testBaseService(){
		DataQueryParam dqp = new DataQueryParam();

		dqp.getDataFields().add("enter");
		dqp.setStartTime(1479600000000l);//2016-11-26
		dqp.setEndTime(1480550400000l);//2016-12-01
		dqp.getGroupBy().setPeriod("All");
		dqp.getSortBy().put("enter",1);
		dqp.setSiteIdList(Arrays.asList(new Integer[] {36}));

		baseService.query(dqp);

	}
}
