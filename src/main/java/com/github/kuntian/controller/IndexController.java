package com.github.kuntian.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	@RequestMapping(value = { "/", "/index" })
	public String index(Model model) {
		String userName = getPrincipal();
		LOGGER.info("登录的用户是[{}]", userName);
		model.addAttribute("userName", userName);
		return "index";
	}

	/**
	 * 返回main页面
	 * 
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasAnyRole('user', 'admin')")
	@RequestMapping(value = "/page/main.html", method = RequestMethod.GET)
	public String getMainPage(Model model) {
		return "page/main";
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
