package com.tianshao.mycalendar.test.interfaces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.tianshao.mycalendar.model.Event;
import com.tianshao.mycalendar.model.IModel;

public interface IModelTest {
	public void testGetCategory();
	
	public void testEqualsAndHash();
	
	public void testDefaultConstructor();//all models need to have default constructor to be compatible with Spring Dependency Injection
	
}
