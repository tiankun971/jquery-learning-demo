package com.github.kuntian.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * wechat mp configuration
 *
 * @author Binary Wang(https://github.com/binarywang)
 */
@Configuration
@EnableConfigurationProperties(JqueryDemoProperties.class)
public class JqueryDemoConfiguration {
  @Autowired
  private JqueryDemoProperties properties;
}
