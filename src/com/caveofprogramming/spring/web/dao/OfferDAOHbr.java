package com.caveofprogramming.spring.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.caveofprogramming.spring.web.model.Offer;


@Repository
public class OfferDAOHbr {

	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	
	public HibernateUtil getHibernateUtil() {
		return hibernateUtil;
	}

	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}

	public OfferDAOHbr() {}
	
	public OfferDAOHbr(HibernateUtil hibernateUtil)
	{
		this.hibernateUtil = hibernateUtil;
	}
	
	
	public Session session()
	{
		return this.getHibernateUtil().getSessionFactory().getCurrentSession();
	}

	public List<Offer> getOffers() {
	
	Criteria criteria = session().createCriteria(Offer.class);
		//criteria.createAlias("offer", "o");
		//criteria.add(Restrictions.eq("u.enabled", true));
		
		
		return criteria.list();
		//return this.getHibernateUtil().getSessionFactory().openSession().createCriteria(Offer.class).list();
		//return this.session().createCriteria("from Offer").list();
	}

	public void Create(Offer offer) {
		
		session().save(offer);
	}

	public Offer getOffer(int i) {
		Criteria criteria = session().createCriteria(Offer.class);
				 criteria.createAlias("offer", "o");
				 //criteria.add(Restrictions.eq("o.enabled", true));
				 criteria.add(Restrictions.idEq(i));
	
		return (Offer)criteria.uniqueResult();		 
	}

	public List<Offer> getOffers(String name) {

		Criteria criteria = session().createCriteria(Offer.class);
				 criteria.createAlias("user", "u");
				 criteria.add(Restrictions.eq("u.enabled",true));
				 criteria.add(Restrictions.eq("u.username",name));
				 
		List<Offer> of = criteria.list();
		return of;	
	}

	public void update(Offer offer) {
		session().update(offer);
		
	}

	public boolean delete(int id) {
	
		Query query = session().createQuery("delete from Offer where id=:id");
		query.setLong("id", id);
		return query.executeUpdate() == 1;
	}
	
	
	
}
