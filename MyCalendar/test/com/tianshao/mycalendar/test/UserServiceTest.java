package com.tianshao.mycalendar.test;

import org.junit.Before;

import com.tianshao.mycalendar.services.EventService;
import com.tianshao.mycalendar.services.UserService;
import com.tianshao.mycalendar.test.interfaces.IServiceTest;

public class UserServiceTest extends IServiceTest{
	UserService mservice=null;
	long []ids={0,0,0};
	@Before
	public void setup() {
		mservice=(UserService) wac.getBean("userService");//new EventServiceImpl();
		
	}
	
	@Override
	public void testAdd() {

		mservice.register("user","pass");
	}

	@Override
	public void testGetLis() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testGet() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testDel() {
		// TODO Auto-generated method stub
		
	}

}
