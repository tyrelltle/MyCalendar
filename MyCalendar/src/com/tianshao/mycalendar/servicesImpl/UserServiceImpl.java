package com.tianshao.mycalendar.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianshao.mycalendar.model.User;
import com.tianshao.mycalendar.repository.DAO;
import com.tianshao.mycalendar.services.UserService;

public class UserServiceImpl implements UserService{
	@Autowired
	DAO dao;
	
	@Override
	public void register(String usr, String psw) {
		/*first check if user exists. otherwise add the user*/
		User user=new User(usr,psw);
		
	}

}
