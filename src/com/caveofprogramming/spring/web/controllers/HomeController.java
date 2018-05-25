package com.caveofprogramming.spring.web.controllers;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caveofprogramming.spring.web.model.Offer;
import com.caveofprogramming.spring.web.service.OffersService;

@Controller
public class HomeController {
	
	
	@Autowired
	private OffersService offersService;
	
	@RequestMapping("/")
	public String showHome(Model model,Principal principal) {
		model.addAttribute("sayfaAdi", "Home Sayfasýndasýnýz.");
		/*List<Offer> offer = offersService.getCurrent();
		model.addAttribute("offers", offer);
		
		
		boolean hasOffer = false;
		if(principal != null)
		{
			hasOffer = offersService.hasOffer(principal.getName());
			
		}
		model.addAttribute("hasOffer", hasOffer);
		*/
		return "home";
	}//FUNC
	
}
