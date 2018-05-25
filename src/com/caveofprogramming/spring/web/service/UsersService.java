package com.caveofprogramming.spring.web.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.caveofprogramming.spring.web.model.Message;
import com.caveofprogramming.spring.web.dao.MessageDAO;
import com.caveofprogramming.spring.web.dao.UsersDAO;
import com.caveofprogramming.spring.web.dao.UsersDAOHbr;
import com.caveofprogramming.spring.web.model.User;

@Transactional
@Service
public class UsersService {

	
	//private UsersDAO usersDao;
	
	 @Autowired
	 //@Qualifier("usersDAOO")
	  private UsersDAOHbr usersDaoo;
	 
	 @Autowired
	 private MessageDAO messageDao;
	  
	public UsersDAOHbr getUsersDaoo() {
		return usersDaoo;
	}

	public void setUsersDaoo(UsersDAOHbr usersDaoo) {
		this.usersDaoo = usersDaoo;
	}

	
	
	public MessageDAO getMessageDao() {
		return messageDao;
	}

	public void setMessageDao(MessageDAO messageDao) {
		this.messageDao = messageDao;
	}

	public void create(User user)
	{
		usersDaoo.create(user);
	}
	
	public boolean exists(String username)
	{
		return usersDaoo.exists(username);
	}

	
	//@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		
		return usersDaoo.getAllUsers();
	}
	
	
	public void sendMessage(Message message)
	{
		messageDao.saveOrUpdate(message);
		
	}
	
	public User getUser(String username)
	{
		return usersDaoo.getUser(username);
	}
	
	public List<Message> getMessages(String username)
	{
		return messageDao.getMessages(username);
	}
	
}
