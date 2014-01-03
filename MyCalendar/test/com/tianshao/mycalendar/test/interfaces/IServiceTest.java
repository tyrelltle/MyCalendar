package com.tianshao.mycalendar.test.interfaces;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.tianshao.mycalendar.test.config.WebAppContext_empty_DAO;
@RunWith(SpringJUnit4ClassRunner.class)

@WebAppConfiguration
@ContextConfiguration(classes=WebAppContext_empty_DAO.class)
public abstract class IServiceTest {
	@Autowired
	protected WebApplicationContext wac;
	@Test
	public void testCRUD(){
		testAdd();
		testGetLis();
		testGet();
		testUpdate();
		testDel();
	}
	public  abstract void testAdd();
	public abstract void testGetLis();
	public abstract void testGet();
	public abstract void testUpdate();
	public abstract void testDel();
	
	
	
}
