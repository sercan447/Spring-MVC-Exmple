package com.caveofprogramming.spring.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.caveofprogramming.spring.web.model.User;



@Repository
public class UsersDAOHbr {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private PasswordEncoder passwordEncode;
	
	public UsersDAOHbr() {}
	public UsersDAOHbr(SessionFactory sessionFactory) 
	{
		this.sessionFactory = sessionFactory;
	}
	
	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<User> getAllUsers() {
		Session session = getSessionFactory().openSession();
			
	return session.createCriteria(User.class).list(); 
		}
	public void create(User user) {
		user.setPassword(passwordEncode.encode(user.getPassword()));
		getSessionFactory().getCurrentSession().save(user);
		
	}
	public boolean exists(String username) {
		Criteria criteria = getSessionFactory().openSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		
		User user = (User)criteria.uniqueResult();
		
		return user != null;
		
	}
	
	public User getUser(String username)
	{
		Criteria criteria =getSessionFactory().openSession().createCriteria(User.class);
		criteria.add(Restrictions.idEq(username));
		
		return (User)criteria.uniqueResult();
	}
	
	
	
}
