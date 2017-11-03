package com.github.kuntian.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.kuntian.service.SUserService;

@Controller
public class MainPageController {

	@Resource
	private SUserService sUserService;

	private static final Logger LOGGER = LoggerFactory.getLogger(MainPageController.class);

	/**
	 * 返回message页面
	 * 
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasAnyRole('user', 'admin')")
	@RequestMapping(value = "/page/message/message.html", method = RequestMethod.GET)
	public String getMessagePage(Model model) {
		return "page/message/message";
	}

	/**
	 * 返回messageReplay页面
	 * 
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasAnyRole('user', 'admin')")
	@RequestMapping(value = "/page/message/messageReply.html", method = RequestMethod.GET)
	public String getmessageReplyPage(Model model) {
		return "page/message/messageReply";
	}

	/**
	 * 返回AllUser页面
	 * 
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasRole('admin')")
	@RequestMapping(value = "/page/user/allUsers.html", method = RequestMethod.GET)
	public String getAllUsersPage(Model model) {
		return "page/user/allUsers";
	}

	/**
	 * 返回addUser页面
	 * 
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasRole('admin')")
	@RequestMapping(value = "/page/user/addUser.html", method = RequestMethod.GET)
	public String addUserPage(Model model) {
		return "page/user/addUser";
	}

	/**
	 * 返回修改密码页面
	 * 
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasAnyRole('user', 'admin')")
	@RequestMapping(value = "/page/user/changePwd.html", method = RequestMethod.GET)
	public String changeUserPwdPage(Model model) {
		return "page/user/changePwd";
	}

	/**
	 * 返回用户信息页面
	 * 
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasAnyRole('user', 'admin')")
	@RequestMapping(value = "/page/user/userInfo.html", method = RequestMethod.GET)
	public String geyUserInfoPage(Model model) {
		return "page/user/userInfo";
	}

	/**
	 * 返回图片总数页面
	 * 
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasAnyRole('user', 'admin')")
	@RequestMapping(value = "/page/img/images.html", method = RequestMethod.GET)
	public String getImagesPage(Model model) {
		return "page/img/images";
	}

	/**
	 * 返回文章页面
	 * 
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasAnyRole('user', 'admin')")
	@RequestMapping(value = "/page/news/newsList.html", method = RequestMethod.GET)
	public String getNewsListPage(Model model) {
		return "page/news/newsList";
	}

	/**
	 * 返回添加文章页面
	 * 
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasAnyRole('user', 'admin')")
	@RequestMapping(value = "/page/news/newsAdd.html", method = RequestMethod.GET)
	public String addNewsPage(Model model) {
		return "page/news/newsAdd";
	}

	/**
	 * 返回友情链接页面
	 * 
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasAnyRole('user', 'admin')")
	@RequestMapping(value = "/page/links/linksList.html", method = RequestMethod.GET)
	public String getLinksListPage(Model model) {
		return "page/links/linksList";
	}

	/**
	 * 返回友情链接添加页面
	 * 
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasAnyRole('user', 'admin')")
	@RequestMapping(value = "/page/links/linksAdd.html", method = RequestMethod.GET)
	public String addLinkPage(Model model) {
		return "page/links/linksAdd";
	}

	/**
	 * 返回系统基本参数页面
	 * 
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasAnyRole('user', 'admin')")
	@RequestMapping(value = "/page/systemParameter/systemParameter.html", method = RequestMethod.GET)
	public String getSystemparameterPage(Model model) {
		return "page/systemParameter/systemParameter";
	}

	/**
	 * 返回404页面
	 * 
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasAnyRole('user', 'admin')")
	@RequestMapping(value = "/page/404.html", method = RequestMethod.GET)
	public String get404Page(Model model) {
		return "page/404";
	}
}
