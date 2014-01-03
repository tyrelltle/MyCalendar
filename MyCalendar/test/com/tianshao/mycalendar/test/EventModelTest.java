package com.tianshao.mycalendar.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tianshao.mycalendar.model.Event;
import com.tianshao.mycalendar.model.IModel;
import com.tianshao.mycalendar.model.User;
import com.tianshao.mycalendar.test.interfaces.IModelTest;

public class EventModelTest extends IModelTest{



	@Test
	public void testEqualsAndHash() {
		IModel a=new Event("1","1","1");
		IModel b=new Event("1","2","2");
		IModel c=new Event("1","1","1");
		assertFalse(a.equals(b));
		assertFalse(a.hashCode()==b.hashCode());
		
		assertFalse(b.equals(c));
		assertFalse(b.hashCode()==c.hashCode());
		
		assertTrue(a.equals(c));
		assertTrue(a.hashCode()==c.hashCode());		
	}


	@Test
	public void testGetCategory() {
		assertEquals(new Event().category(),"Events");
		
	}


	@Test
	public void testDefaultConstructor() {
		try{
			Event u=new Event();
		}catch(Exception e){
			fail(e.getMessage());
		}
		
	}
}
