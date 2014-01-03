package com.tianshao.mycalendar.DBTest;

import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.tianshao.mycalendar.model.Event;
import com.tianshao.mycalendar.model.IModel;
import com.tianshao.mycalendar.repository.DAO;
import com.tianshao.mycalendar.repository.DAOAppEngineImpl;

import static com.google.appengine.api.datastore.FetchOptions.Builder.withLimit;
import static org.junit.Assert.*;

public class EventDAOAppEngineImplTest {
	private final LocalServiceTestHelper helper =
	        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	DAO dao=new DAOAppEngineImpl();
	@Before
	public void setup(){
		helper.setUp();
	}
	@After
	public void after(){
		helper.tearDown();
	}
	@Test
	public void testAdd() throws IllegalArgumentException, IllegalAccessException, InstantiationException{
	    
	   Event e=new Event("1","1","1");
	   long id=dao.add(e);
	   e.id=id;
	   Event ret=(Event) dao.get(id,Event.class);
	   assertTrue(ret.equals(e));
	}
	
	@Test
	public void testGetEvents() throws IllegalArgumentException, IllegalAccessException, InstantiationException{
		ArrayList<? extends IModel> events;
		for(int i=0;i<10;i++)
		{
			Event e=new Event(String.valueOf(i),String.valueOf(i),String.valueOf(i));
			dao.add(e);
		}
		events=dao.getList(Event.class);
		for(int i=0;i<10;i++)
		{
			Event e=(Event) events.get(i);
			assertEquals(e.detail,String.valueOf(i));
			assertEquals(e.loc,String.valueOf(i));
			assertEquals(e.title,String.valueOf(i));

		}
	}
	@Test
	public void testUpdate() throws IllegalArgumentException, IllegalAccessException, InstantiationException{
		Event e=new Event("init","init","init");
		long id=dao.add(e);
		e=new Event("no","no","no");
		dao.update(e,id);
		e=(Event) dao.get(id,Event.class);
		assertEquals(e.detail,"no");
		assertEquals(e.loc,"no");
		assertEquals(e.title,"no");
	}
	
	@Test
	public void testDel() throws InstantiationException, IllegalAccessException{
		Event e=new Event("del","del","del");
		long id=dao.add(e);
		dao.del(id,Event.class);
		assertEquals(dao.get(id,Event.class),null);
		
	}
}
