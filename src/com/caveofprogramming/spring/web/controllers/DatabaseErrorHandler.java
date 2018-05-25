package com.caveofprogramming.spring.web.controllers;

import java.nio.file.AccessDeniedException;

import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DatabaseErrorHandler {

		@ExceptionHandler(DataAccessException.class)
		public String handleDatabaseException(Exception e,Model model)
		{
			model.addAttribute("hataverisi",e.toString());
			return "error";
		}
		
		@ExceptionHandler(AccessDeniedException.class)
		public String handleAccessException(AccessDeniedException ex)
		{
			return "denied";
		}
	
	 
}
