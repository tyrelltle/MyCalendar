package com.tianshao.mycalendar.repository;

import java.util.ArrayList;

import com.tianshao.mycalendar.model.Event;
import com.tianshao.mycalendar.model.IModel;

public interface DAO {

	public ArrayList<? extends IModel> getList(Class c) throws InstantiationException, IllegalAccessException;
	public void update(IModel e, long id) throws IllegalArgumentException, IllegalAccessException;
	public void del(long id,Class c) throws InstantiationException, IllegalAccessException;
	public IModel get(long id,Class c) throws IllegalArgumentException, IllegalAccessException, InstantiationException;
	public long add(IModel e) throws IllegalArgumentException, IllegalAccessException;
}
