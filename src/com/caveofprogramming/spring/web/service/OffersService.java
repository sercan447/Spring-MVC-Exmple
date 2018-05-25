package com.caveofprogramming.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.caveofprogramming.spring.web.dao.OfferDAO;
import com.caveofprogramming.spring.web.dao.OfferDAOHbr;
import com.caveofprogramming.spring.web.model.Offer;

@Transactional
@Service
public class OffersService {

	//	private OfferDAO offersDao;
	
	@Autowired
	private OfferDAOHbr offersDaoo;
	

	public OfferDAOHbr getOffersDaoo() {
		return offersDaoo;
	}

	public void setOffersDaoo(OfferDAOHbr offersDaoo) {
		this.offersDaoo = offersDaoo;
	}

	//@Secured("ROLE_ADMIN")
	public List<Offer> getCurrent()
	{
		return offersDaoo.getOffers();
	}
	
	//@Secured({"ROLE_ADMIN"})
	public void create(Offer offer) 
	{
		offersDaoo.Create(offer);
	}
	
	public void throwTestException()
	{
		offersDaoo.getOffer(34);
	}


	public boolean hasOffer(String name) {
		
		if(name == null) 
			return false;
		
		List<Offer> offers = offersDaoo.getOffers(name);
		
		if(offers.size() == 0)
		{
			return false;
		}
		
		return true;
	}//FUNC
	
	
	public Offer getOffer(String username)
	{
		if(username == null)
		{
			return null;
		}
		List<Offer> offers = offersDaoo.getOffers(username);
		
		if(offers.size() == 0)
		{
			return null;
		}
		
		return offers.get(0);
	}//FUNC
	
	public void saveOrUpdate(Offer offer)
	{
		if(offer.getId() != 0)
		{
			offersDaoo.update(offer);
		}else {
			offersDaoo.Create(offer);
		}
		
	}//FUNC
	
	public void delete(int id)
	{
		offersDaoo.delete(id);
	}
	
	
	
}
