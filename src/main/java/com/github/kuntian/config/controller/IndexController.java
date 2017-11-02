package com.github.kuntian.config.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author kun.tian(https://github.com/tiankun971)
 */
@Controller
public class IndexController {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	/**
	 * index page, user can be navigated to index page.
	 * 
	 * @param model
	 *            current page model
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Model model) {
		String userName = getPrincipal();
		LOGGER.info("登录的用户是[{}]", userName);
		model.addAttribute("userName", userName);
		return "index";
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			UserDetails userDetail = (UserDetails) principal;
			userName = userDetail.getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
}
