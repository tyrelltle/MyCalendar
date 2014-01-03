package com.tianshao.mycalendar.test.mocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.annotation.Bean;

import com.tianshao.mycalendar.model.Event;
import com.tianshao.mycalendar.model.IModel;
import com.tianshao.mycalendar.repository.DAO;

public class MockDAO  implements DAO {
	List<Event> events=new ArrayList();
	long nexid=0;


/**************real overrides
 * @throws IllegalAccessException 
 * @throws IllegalArgumentException ****************/
	public void setEvents() throws IllegalArgumentException, IllegalAccessException
	{
	    	this.add(new Event("title1","loc1","detail1"));
	    		  
	    	this.add(new Event("title2","loc2","detail2"));
	    	
	    	this.add(new Event("title3","loc3","detail3"));


	}





	@Override
	public ArrayList<? extends IModel> getList(Class c)
			throws InstantiationException, IllegalAccessException {
	
		
			return (ArrayList<? extends IModel>) events;
		
	}

	@Override
	public void update(IModel e, long id) throws IllegalArgumentException,
			IllegalAccessException {
		
			Event event;
			try {
				event = (Event) this.get(id, e.getClass());
				event.loc=((Event)e).loc;
				event.title=((Event)e).title;
				event.detail=((Event)e).detail;
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
	}

	@Override
	public void del(long id, Class c) throws InstantiationException,
			IllegalAccessException {

		
			 events.remove((int)id);		
		
	}

	@Override
	public IModel get(long id, Class c) throws IllegalArgumentException,
			IllegalAccessException, InstantiationException {


			Iterator<Event> i=events.iterator();
			while(i.hasNext())
			{
				Event e=i.next();
				if(e.id==id)
					return e;
			}
			return null;
		
	}

	@Override
	public long add(IModel e) throws IllegalArgumentException,
			IllegalAccessException {

	
			if(e!=null){
				e.id=nexid++;
				events.add((Event) e);
			}
			return e.id;
	}
}
