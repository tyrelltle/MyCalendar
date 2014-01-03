package com.tianshao.mycalendar.test.interfaces;

import org.junit.Test;

public abstract class IServiceTest {
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
