package com.caveofprogramming.spring.web.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caveofprogramming.spring.web.model.Message;
import com.caveofprogramming.spring.web.model.User;
import com.caveofprogramming.spring.web.service.UsersService;

@Controller
public class LoginController {
	
	
	@Autowired
	private UsersService UsersService;	
	
	@RequestMapping("/login")
	public String showLogin()
	{
		return "login";  
	}
	
	@RequestMapping("/denied")
	public String showDenied()
	{
		return "denied";
	}
	
	@RequestMapping("/admin")
	public String showAdmin(Model model)
	{
		try {
			
			List<User> users = UsersService.getAllUsers();
			
			model.addAttribute("users",users);
			
		}catch(Exception e) {
			System.out.println("Hatalandi : "+e);
		}
		
		
		return "admin";
	}
			
	@RequestMapping("/loggedout")
	public String showLoggedOut()
	{
		return "loggedout";
	}
	
	
	@RequestMapping("/newaccount")
	public String showNewAccount(Model model)
	{
		model.addAttribute("sayfaAdi", "Kullanici Oluþturma");
		model.addAttribute("user",new User());
		
		return "newaccount";
	}
	
	/*@RequestMapping("/createaccount")
	public String createAccount()
	{
		return "accountcreated";
	}
	*/
	@RequestMapping(value="/createaccount",method=RequestMethod.POST)
	public String createaAccount(@Valid User user,BindingResult result) {
		
		if(result.hasErrors())
		{
			return "newaccount";
		}
		
		user.setAuthority("ROLE_USER");
		user.setEnabled(true);
		
		if(UsersService.exists(user.getUsername()))
		{
			System.out.println("Cought duplicate username.	");
			result.rejectValue("username","DuplicateKey.user.username");
			return "newaccount";
		}
		
		try {
			UsersService.create(user);
		}catch(DuplicateKeyException dup)
		{
			result.rejectValue("username","DuplicateKey.user.username");
			return "newaccount";
		}finally {
			
		}
		
		
		return "accountcreated";
	}//func

	
	@RequestMapping(value="/sebeb/{num}",method=RequestMethod.GET , produces= "text/application")
	public @ResponseBody String mesaj(@PathVariable("num") int id)
	{
		return "mesajlar : "+id;
	}
	
	@RequestMapping(value="/getmessages",method=RequestMethod.GET,produces="application/json")
	@ResponseBody
	public Map<String,Object> getMessage(Principal principal)
	{
		List<Message> messages = null;
		
		if(principal == null)
		{
			messages = new ArrayList<Message>();
		}else {
			String username = principal.getName();
			messages = UsersService.getMessages(username);
		}
		
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("messages", messages);
		data.put("number",messages.size());
		
		return data;
		
	}
	
	@RequestMapping(value="/sendmessage",method=RequestMethod.POST,produces="application/json")
	@ResponseBody
	public Map<String,Object> sendMessage(Principal principal,@RequestBody Map<String,Object> data)
	{
		String text = (String)data.get("text");
		String name = (String)data.get("name");
		String email = (String)data.get("email");
				
		System.out.println(name+","+email+","+text);
		
		Map<String,Object> rval = new HashMap<String,Object>();
						rval.put("success", true);	
		
		return rval;
		
	}
	
	
}
