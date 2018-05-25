package com.caveofprogramming.spring.web.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.caveofprogramming.spring.web.model.User;

  
public class UsersDAO {

	
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	private PasswordEncoder passwordEncode;
	
	@Autowired
	public void setDataSource(DataSource jdbc)
	{
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}	
	
	@Transactional
	public boolean create(User user)
	{
		//BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		MapSqlParameterSource params = new MapSqlParameterSource();
							params.addValue("username", user.getUsername());
							params.addValue("password",passwordEncode.encode(user.getPassword()));
							params.addValue("name",user.getName());
							params.addValue("email", user.getEmail());
							params.addValue("enabled", user.isEnabled());
							params.addValue("authority", "ROLE_USER");
							
	 jdbc.update("INSERT INTO users(username,password,name,email,enabled,authority) VALUES (:username,:password,:name,:email,:enabled,:authority)", params);
		
		return jdbc.update("INSERT INTO authorities(username,authority) VALUES (:username,:authority)", params) == 1;
	}
	
	public boolean exists(String username)
	{
		return jdbc.queryForObject("SELECT COUNT(*) FROM users WHERE username=:username",
									new MapSqlParameterSource("username",username),Integer.class) > 0;
		
	}

	public List<User> getAllUsers() {
		
		
		return jdbc.query("SELECT * FROM users ",BeanPropertyRowMapper.newInstance(User.class) );
	}
}
