package com.github.kuntian.config.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author kun.tian(https://github.com/tiankun971)
 */
@Controller
@RequestMapping("/portal")
public class JqueryDemoController {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  /**
   * index page, user can be navigated to index page.
   * 
   * @param model
   *          current page model
   * @return
   */
  @RequestMapping("/index")
  public String index(Model model) {
    model.addAttribute("welcome", "田坤， 欢迎！ 你已经进入了主页！");
    logger.info("{}， 欢迎！你已经进入了主页！", "田坤");
    return "index";
  }
}
