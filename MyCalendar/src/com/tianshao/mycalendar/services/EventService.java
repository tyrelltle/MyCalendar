package com.tianshao.mycalendar.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.tianshao.mycalendar.model.Event;

public interface EventService{

	public abstract long addEvent(Event event);

	public abstract List<Event> getEvents();

	public abstract Event getEvent(long id);

	public abstract Event removeEvent(long i);

	public abstract void update(long i, Event e);
}
