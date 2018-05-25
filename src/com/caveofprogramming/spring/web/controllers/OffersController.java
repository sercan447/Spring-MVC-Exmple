package com.caveofprogramming.spring.web.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.caveofprogramming.spring.web.model.Offer;
import com.caveofprogramming.spring.web.service.OffersService;

@Controller
public class OffersController {
	
	
	/*@RequestMapping("/")
	public String showHome(HttpSession session)
	{
		session.setAttribute("name","SERCANUS");
		return "home";
	}
	*/
	
	/*@RequestMapping("/")
	public ModelAndView showHome()
	{
		ModelAndView v = new ModelAndView("home");
		Map<String,Object> model = v.getModel();
		
		model.put("name","Müfredat Gerginim");
		
		return v;
	}
	*/
	
	@Autowired
	private OffersService OffersService;
	
	
	public void setOffersService(OffersService offersService) {
		OffersService = offersService;
	}

		
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public String showTest(Model model,@RequestParam("num")String num)
	{
		System.out.println("nUMARA : "+num);
		return "home";
				
	}
	
	
	/*
	 @ExceptionHandler(DataAccessException.class)
	public String handleDatabaseException(DataAccessException e)
	{
		return "error";
	}
	*/
	
	@RequestMapping("/offers")
	public String showOffers(Model model)
	{
		//HATALANIYOR
		//OffersService.throwTestException();
		
		List<Offer> offers = OffersService.getCurrent();
			
		model.addAttribute("offers",offers);
		
		return "offers";
	}
	
	@RequestMapping("/createoffer")
	public String createOffer(Model model,Principal principal)
	{
	   Offer offer = null;
		
		if(principal != null)
		{
			String username = principal.getName();
			 offer = OffersService.getOffer(username);
		}
		
		if(offer == null)
		{
			offer = new Offer();
		}
		
		model.addAttribute("sayfaAdi", "Createa Offer Sayfasý");
		model.addAttribute("offer", offer);
		
		return "createoffer";
	}
	
	@RequestMapping(value="/docreate",method=RequestMethod.POST)
	public String doCreate(Model model,@Valid Offer offer,BindingResult result
												,@RequestParam(value="delete",required=false)String delete)
	{
		
		if(result.hasErrors())
		{
			return "createoffer";
		}
		if(delete == null)
		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String name = auth.getName();
			offer.getUser().setUsername(name);
			
			
			//OffersService.create(offer);
			OffersService.saveOrUpdate(offer);
			
			
			return "offercreated";
		}else {
			OffersService.delete(offer.getId());
			return "deletecreated";
		}
				
	}//FUNC

}
