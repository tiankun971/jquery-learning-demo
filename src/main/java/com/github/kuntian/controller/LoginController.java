package com.github.kuntian.controller;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.kuntian.service.SUserService;

@Controller
public class LoginController {

	private static final String SPRING_SECURITY_LAST_EXCEPTION = "SPRING_SECURITY_LAST_EXCEPTION";
	@Resource
	private SUserService sUserService;

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private MessageSource messageSource;

	private Locale locale = LocaleContextHolder.getLocale();

	/**
	 * 返回session中保存的验证码数值
	 * 
	 * @param model
	 *            current page model
	 * @return
	 */
	@RequestMapping("/getKaptchaSessionKey")
	public @ResponseBody boolean getKaptchaSessionKey(@RequestParam(value = "code", required = true) String code,
			HttpServletRequest request) {
		String capText = (String) request.getSession().getAttribute("kaptcha.code");
		return LoginController.equals(capText, code);
	}

	/**
	 * Constant time comparison to prevent against timing attacks.
	 * 
	 * @param expected
	 * @param actual
	 * @return
	 */
	static boolean equals(String expected, String actual) {
		byte[] expectedBytes = bytesUtf8(expected);
		byte[] actualBytes = bytesUtf8(actual);
		int expectedLength = expectedBytes == null ? -1 : expectedBytes.length;
		int actualLength = actualBytes == null ? -1 : actualBytes.length;
		if (expectedLength != actualLength) {
			return false;
		}

		int result = 0;
		for (int i = 0; i < expectedLength; i++) {
			result |= expectedBytes[i] ^ actualBytes[i];
		}
		return result == 0;
	}

	private static byte[] bytesUtf8(String s) {
		if (s == null) {
			return null;
		}

		return Utf8.encode(s);
	}

	/**
	 * this is a main enter to our system.
	 * 
	 * 
	 * @return return to login page
	 */
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, SPRING_SECURITY_LAST_EXCEPTION));
		}

		if (logout != null) {
			String message = messageSource.getMessage("LogOut.success", null, locale);
			model.addObject("msg", message);
		}
		model.setViewName("login");

		return model;
	}

	/**
	 * 公共处理模块，所以access Deny都统一跳转到这个页面
	 * 
	 * @return 画面跳转到access Deny页面
	 * 
	 */
	@RequestMapping(value = "/common/403.html", method = RequestMethod.GET)
	public String error(ModelMap model) {
		model.addAttribute("userName", getPrincipal());
		return "403";
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

	/**
	 * customize the error message
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";

		if (exception instanceof BadCredentialsException) {
			error = messageSource.getMessage("Login.invalidUsernameAndPassword", null, locale);
			LOGGER.info("显示的内容是[{}]", error);
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = messageSource.getMessage("Login.invalidUsernameAndPassword", null, locale);
			LOGGER.info("显示的内容是[{}]", error);
		}

		return error;
	}
}
