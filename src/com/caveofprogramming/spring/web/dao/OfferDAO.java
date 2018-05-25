package com.caveofprogramming.spring.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;

import com.caveofprogramming.spring.web.model.Offer;

//@Component("offersDAO")
public class OfferDAO {
	
	public OfferDAO() {
		System.out.println("BASARILI dao iþlemmi");
	}
	
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc)
	{
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	public List<Offer> getOffers()
	{
	return jdbc.query("SELECT * FROM offers,users where offers.username = users.username and users.enabled=true"
			,new OfferRowMapper());
		
		
	}//FUNC
	public List<Offer> getOffers(String username)
	{
	return jdbc.query("SELECT * FROM offers,users where offers.username = users.username and users.enabled=true and offers.username=:username"
			,new MapSqlParameterSource("username",username),new OfferRowMapper());
	}//FUNC
	
	public Offer getOffer(int id)
	{
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id",id);
		
		return jdbc.queryForObject("SELECT * FROM offers WHERE id=:id",params, new OfferRowMapper());	
	}//FUNC
	public Offer getOffer(String name)
	{
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username",name);
		
		return jdbc.queryForObject("SELECT * FROM offers WHERE username=:username",params, new OfferRowMapper());	
	}//FUNC
	
	public boolean Create(Offer offer)
	{
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
		
		return jdbc
				.update("insert into offers (username, text) values (:username, :text)",params) == 1;
	}//FUNC
	
	public boolean update(Offer offer)
	{
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
		return jdbc.update("update offers set text=:text where id=:id ", params) == 1;
	}
	
	public int[] create(List<Offer> offers)
	{
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(offers.toArray());
		
		return jdbc.batchUpdate("insert into offers(username,text) values (:username,:text)", params);
	}
	
	
	public boolean delete(int id)
	{
		MapSqlParameterSource params = new MapSqlParameterSource("id",id);
		
		return jdbc.update("delete from offers where id=:id",params) == 1;
	}

}
