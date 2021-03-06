package com.tianshao.mycalendar.test;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.tianshao.mycalendar.model.Event;
import com.tianshao.mycalendar.services.EventService;
import com.tianshao.mycalendar.servicesImpl.EventServiceImpl;
import com.tianshao.mycalendar.test.config.WebAppContext;
import com.tianshao.mycalendar.test.config.WebAppContext_empty_DAO;
import com.tianshao.mycalendar.test.interfaces.IServiceTest;

public class EventServiceTest extends IServiceTest{

	EventService mservice=null;
	long []ids={0,0,0};
	@Before
	public void setup() {
		mservice=(EventService) wac.getBean("eventService");//new EventServiceImpl();
		
	}
	

	public void testAdd() {
		ids[0]= mservice.addEvent(new Event("asd", "asd", "asd"));
		ids[1]= mservice.addEvent(new Event("asd1", "asd1", "asd1"));
		ids[2]= mservice.addEvent(new Event("asd2", "asd2", "asd2"));		
	}

	public void testUpdate(){
		Event e=new Event("updated","updated","updated");
		
		mservice.update(ids[2],e);
		assertEquals(mservice.getEvent(ids[2]).title,"updated");
		assertEquals(mservice.getEvent(ids[2]).detail,"updated");
		assertEquals(mservice.getEvent(ids[2]).loc,"updated");

	}
	public void testGetLis(){
		
		List<Event> es=mservice.getEvents();
		boolean one=false,two=false,three=false;
		Iterator<Event> i=es.iterator();
		
		while(i.hasNext())
		{
			Event e=i.next();
			if(e.detail.equals("asd")&&
			   e.title.equals("asd")&&
			   e.loc.equals("asd"))
				one=true;
			else if(e.detail.equals("asd1")&&
					   e.title.equals("asd1")&&
					   e.loc.equals("asd1"))
				two=true;
			else if (e.detail.equals("asd2")&&
					   e.title.equals("asd2")&&
					   e.loc.equals("asd2"))
				three=true;
		}
		assert(one&&two&&three);
	}
	
	
	public void testGet(){
		assertEquals(mservice.getEvent(ids[0]).detail,"asd");
		assertEquals(mservice.getEvent(ids[1]).detail,"asd1");
		assertEquals(mservice.getEvent(ids[2]).detail,"asd2");

		
	}
	
	
	public void testDel(){
		mservice.removeEvent(ids[0]);
		assertEquals(mservice.getEvent(ids[0]),null);

		
	}
	

}
