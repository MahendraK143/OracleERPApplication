package com.oracle.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.model.User;

@Controller
public class LoginController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(ModelMap map) {
		map.put("navStatus", false);
		return "login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("user") User user, HttpServletRequest request, HttpServletResponse response,ModelMap map) {
		map.put("navStatus", false);
		map.put("navStatus", true);
		map.put("TabActive", "DashBoard");
		request.getSession().setAttribute("navStatus", true);
		return "home";
	}

}
