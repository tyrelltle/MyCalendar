package com.tianshao.mycalendar.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tianshao.mycalendar.model.Event;

public class ModelTest {
	@Test
	public void testGetCategory(){
		assertEquals(new Event().category(),"Events");
	}
	
	
	@Test
	public void testEqualsAndHash(){
		Event a=new Event("1","1","1");
		Event b=new Event("1","2","2");
		Event c=new Event("1","1","1");
		assertFalse(a.equals(b));
		assertFalse(a.hashCode()==b.hashCode());
		
		assertFalse(a.equals(b));
		assertFalse(a.hashCode()==b.hashCode());
		
		assertTrue(a.equals(c));
		assertTrue(a.hashCode()==c.hashCode());
	}
}
