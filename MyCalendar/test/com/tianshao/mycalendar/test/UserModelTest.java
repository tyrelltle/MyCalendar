package com.tianshao.mycalendar.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tianshao.mycalendar.model.Event;
import com.tianshao.mycalendar.model.IModel;
import com.tianshao.mycalendar.model.User;
import com.tianshao.mycalendar.test.interfaces.IModelTest;

public class UserModelTest extends IModelTest{



	@Test
	public void testEqualsAndHash() {
		IModel a=new User("tyrelltle@gmail.com","wanysysbb");
		IModel b=new User("tyrellstle@gmail.com","wanysysbb");
		IModel c=new User("tyrelltle@gmail.com","wanysysbb");

		assertFalse(a.equals(b));
		assertFalse(a.hashCode()==b.hashCode());
		
		assertFalse(b.equals(c));
		assertFalse(b.hashCode()==c.hashCode());
		
		assertTrue(a.equals(c));
		assertTrue(a.hashCode()==c.hashCode());		
	}


	@Test
	public void testGetCategory() {
		assertEquals(new User().category(),"Users");
		
	}


	@Test
	public void testDefaultConstructor() {
		try{
			User u=new User();
		}catch(Exception e){
			fail(e.getMessage());
		}
		
	}
}
