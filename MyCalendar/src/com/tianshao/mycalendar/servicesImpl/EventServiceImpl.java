package com.tianshao.mycalendar.servicesImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianshao.mycalendar.model.Event;
import com.tianshao.mycalendar.repository.DAO;
import com.tianshao.mycalendar.services.EventService;
@Service
public class EventServiceImpl implements EventService, Serializable {
	@Autowired
	DAO dao;

	@Override
	public long addEvent(Event event)  {
		try {
			return dao.add(event);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		
	}


	@Override
	public List<Event> getEvents()  {
		try {
			return (List<Event>) dao.getList(Event.class);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		/*return Collections.unmodifiableList(events);*/
	}


	@Override
	public Event getEvent(long id) {
		
		try {
			return (Event) dao.get(id, Event.class);
		} catch (IllegalArgumentException | IllegalAccessException |InstantiationException e ){
			throw new RuntimeException(e);
		}
	}


	@Override
	public Event removeEvent(long i) {
		Event ret=null;
		try{
			ret=(Event) dao.get(i, Event.class);
			dao.del(i, Event.class);
		} catch (IllegalArgumentException | IllegalAccessException |InstantiationException e ){
			throw new RuntimeException(e);
		}
		return ret;		
	}

	@Override
	public void update(long i, Event e) {

		try {
			dao.update(e, i);
		} catch (IllegalArgumentException | IllegalAccessException e1) {
			throw new RuntimeException(e1);
		}
	}
	


}
