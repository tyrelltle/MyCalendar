package com.tianshao.mycalendar.DBTest;

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
import static com.google.appengine.api.datastore.FetchOptions.Builder.withLimit;
import static org.junit.Assert.*;

public class dbtryout {
	private final LocalServiceTestHelper helper =
	        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	@Before
	public void setup(){
		helper.setUp();
	}
	@After
	public void after(){
		helper.tearDown();
	}
	@Test
	public void test(){
	    DatastoreService ds = DatastoreServiceFactory.getDatastoreService();

	    Entity newe=new Entity("yam");
	    newe.setProperty("name", "myname");
	    
	    Key k=ds.put(newe);
	    
        Key computedKey=KeyFactory.createKey("yam", k.getId());
        
        Query q=new Query("yam").addFilter(Entity.KEY_RESERVED_PROPERTY,
                Query.FilterOperator.GREATER_THAN,
                computedKey);
        
        
        //assertEquals(2, ds.prepare(new Query("yam")).countEntities(withLimit(10)));
	}
}
