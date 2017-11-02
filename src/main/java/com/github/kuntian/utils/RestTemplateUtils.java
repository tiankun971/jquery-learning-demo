package com.github.kuntian.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

/**
 * @Title: RestTemplateUtils.java
 * @Prject: wms_website
 * @Package: com.eal.cbec.wms.utils
 * @Description: TODO
 * @author: kun.tian
 * @date: 2017年10月23日 下午2:07:18
 * @version: V1.0
 */
public class RestTemplateUtils {

	/**
	 * @ClassName: DefaultResponseErrorHandler
	 * @Description: TODO
	 * @author: kun.tian
	 * @date: 2017年10月23日 下午2:07:18
	 */
	private static class DefaultResponseErrorHandler implements ResponseErrorHandler {

		@Override
		public boolean hasError(ClientHttpResponse response) throws IOException {
			return response.getStatusCode().value() != HttpServletResponse.SC_OK;
		}

		@Override
		public void handleError(ClientHttpResponse response) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getBody()));
			StringBuilder sb = new StringBuilder();
			String str = null;
			while ((str = br.readLine()) != null) {
				sb.append(str);
			}
			try {
				throw new Exception(sb.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static final boolean String = false;

	/**
	 * @param url
	 * @param params
	 * @return
	 * @Title: get
	 * @author: kun.tian
	 * @Description: TODO
	 * @return: String
	 */
	public static String get(RestTemplate restTemplate, String url, JSONObject params) {
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
		String response = restTemplate.getForObject(expandURL(url, params.keySet()), String.class, params);
		return response;
	}

	/**
	 * @param url
	 * @param params
	 * @param mediaType
	 * @return
	 * @Title: post
	 * @author: kun.tian
	 * @Description: 将参数都拼接在url之后
	 * @return: String
	 */
	public static String post(RestTemplate restTemplate, String url, JSONObject params, MediaType mediaType) {
		// 拿到header信息
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(mediaType);
		HttpEntity<JSONObject> requestEntity = (mediaType == MediaType.APPLICATION_JSON
				|| mediaType == MediaType.APPLICATION_JSON_UTF8) ? new HttpEntity<JSONObject>(params, requestHeaders)
						: new HttpEntity<JSONObject>(null, requestHeaders);
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
		String result = (mediaType == MediaType.APPLICATION_JSON || mediaType == MediaType.APPLICATION_JSON_UTF8)
				? restTemplate.postForObject(url, requestEntity, String.class)
				: restTemplate.postForObject(expandURL(url, params.keySet()), requestEntity, String.class, params);
		return result;
	}

	/**
	 * @param url
	 * @param params
	 * @param mediaType
	 * @param clz
	 * @return
	 * @Title: post
	 * @author: kun.tian
	 * @Description: 发送json或者form格式数据
	 * @return: String
	 */
	public static <T> T post(RestTemplate restTemplate, String url, JSONObject params, MediaType mediaType,
			Class<T> clz, HttpServletRequest request) {
		// 这是为 MediaType.APPLICATION_FORM_URLENCODED 格式HttpEntity 数据 添加转换器
		// 还有就是，如果是APPLICATION_FORM_URLENCODED方式发送post请求，
		// 也可以直接HttpHeaders requestHeaders = new
		// HttpHeaders(createMultiValueMap(params)，true)，就不用增加转换器了
		restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
		// 设置header信息
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(mediaType);
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			requestHeaders.add(key, value);
		}

		HttpEntity<?> requestEntity = (mediaType == MediaType.APPLICATION_JSON
				|| mediaType == MediaType.APPLICATION_JSON_UTF8)
						? new HttpEntity<JSONObject>(params, requestHeaders)
						: (mediaType == MediaType.APPLICATION_FORM_URLENCODED
								? new HttpEntity<MultiValueMap>(createMultiValueMap(params), requestHeaders)
								: new HttpEntity<>(null, requestHeaders));

		restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
		T result = (mediaType == MediaType.APPLICATION_JSON || mediaType == MediaType.APPLICATION_JSON_UTF8)
				? restTemplate.postForObject(url, requestEntity, clz)
				: restTemplate.postForObject(
						mediaType == MediaType.APPLICATION_FORM_URLENCODED ? url : expandURL(url, params.keySet()),
						requestEntity, clz, params);

		return result;
	}

	private static MultiValueMap<String, String> createMultiValueMap(JSONObject params) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		for (String key : params.keySet()) {
			if (params.get(key) instanceof List) {
				for (Iterator<String> it = ((List<String>) params.get(key)).iterator(); it.hasNext();) {
					String value = it.next();
					map.add(key, value);
				}
			} else {
				map.add(key, params.getString(key));
			}
		}
		return map;
	}

	public static <T> T post(RestTemplate restTemplate, String url, Class<T> clz, HttpServletRequest request) {
		restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
		// 设置header信息
		HttpHeaders requestHeaders = new HttpHeaders();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			requestHeaders.add(key, value);
		}

		HttpEntity<?> requestEntity = new HttpEntity<MultiValueMap>(createMultiValueMap(request), requestHeaders);

		restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
		T result = restTemplate.postForObject(url, /* 拷贝的请求报文头 */requestEntity, clz,
				/* 携带parameter */request.getParameterMap());

		return result;
	}

	private static MultiValueMap<String, String> createMultiValueMap(HttpServletRequest request) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		for (String key : request.getParameterMap().keySet()) {
			java.lang.String[] values = request.getParameterMap().get(key);

			for (int index = 0; index < values.length; index++) {
				String value = values[index];
				map.add(key, value);
			}
		}
		return map;
	}

	/**
	 * @param url
	 * @param keys
	 * @return
	 * @Title: expandURL
	 * @author: kun.tian
	 * @Description: TODO
	 * @return: String
	 */
	private static String expandURL(String url, Set<?> keys) {
		final Pattern QUERY_PARAM_PATTERN = Pattern.compile("([^&=]+)(=?)([^&]+)?");
		Matcher mc = QUERY_PARAM_PATTERN.matcher(url);
		StringBuilder sb = new StringBuilder(url);
		if (mc.find()) {
			sb.append("&");
		} else {
			sb.append("?");
		}

		for (Object key : keys) {
			sb.append(key).append("=").append("{").append(key).append("}").append("&");
		}
		return sb.deleteCharAt(sb.length() - 1).toString();
	}
}
