package com.github.kuntian.utils;


import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * 日期操作方法集。
 * 
 * @author Yang Yihua
 */

public class DateUtils {

	/**
	 * 默认日期格式，默认格式为<b>yyyy-MM-dd</b>
	 */
	public static String format = "yyyy-MM-dd";
	
	public static String fullFormat = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 默认日期格式，默认格式为<b>"yyyy-MM-dd HH:mm:ss:SSS";</b>
	 */
	public static String SSS_Format = "yyyy-MM-dd HH:mm:ss:SSS";
	public static String formatTime = "MM/dd/yyyy HH:mm";
	public static String formatTime2 = "yyyy-MM-dd HH:mm";
	/**
	 * 使用默认格式来格式化日期。
	 * 
	 * @param date
	 *            需要格式化的日期。
	 * 
	 * @return 格式化后的日期字符串。如果date为null，则返回空字符串。
	 */
	public static String formatDate(Date date) {
		return formatDate(date, "");
	}

	/**
	 * 使用指定的格式来格式化日期。默认格式为<b>yyyy-MM-dd</b>
	 * 
	 * @param formatString
	 *            需要格式化的日期。
	 * 
	 * @param format
	 *            指定日期的格式化字符串。如果为空字符串或者null，则使用默认的格式来 格式化日期。
	 * 
	 * @return 格式化后的日期字符串。如果date为null，则返回空字符串。
	 */
	public static String formatDate(Date date, String formatString) {

		String result = "";
		SimpleDateFormat sdf = null;

		try {

			if ((formatString == null) || (formatString.length() <= 0)) {
				formatString = format;
			}

			sdf = new SimpleDateFormat(formatString);
			result = sdf.format(date);

		} catch (Exception e) {
			result = "";

		}
		return result;

	}

	/**
	 * 使用默认的字符串格式，解析字符串并生成日期对象。默认格式为<b>yyyy年MM月dd日</b>
	 * 
	 * @param parseString
	 *            需要解析的字符串。
	 * 
	 * @return 根据指定的字符串格式生成的日期对象。如果parseString为空字符串或者null， 返回日期对象为null。
	 */
	public static Date parseDate(String parseString) {
		return parseDate(parseString, "");
	}

	/**
	 * 根据指定的字符串格式，解析字符串并生成日期对象。
	 * 
	 * @param parseString
	 *            需要解析的字符串。
	 * 
	 * @param formatString
	 *            指定的字符串格式。
	 * 
	 * @return 根据指定的字符串格式生成的日期对象。如果parseString为空字符串或者null，
	 *         返回日期对象为null。如果formatString为空字符串或者null，则使用默认日期格式解析。
	 */
	public static Date parseDate(String parseString, String formatString) {

		Date result = null;
		SimpleDateFormat sdf = null;

		try {
			if ((parseString != null) && (parseString.length() > 0)) { // paesrString是正确的，准备解析

				if ((formatString == null) || (formatString.length() <= 0)) { // 没有指定格式，使用默认格式。
					formatString = format;
				}

				sdf = new SimpleDateFormat(formatString);
				sdf.setLenient(false);
				result = sdf.parse(parseString);
			}

		} catch (Exception e) {
			
		}
		return result;
	}
	   /**  @desc:  取得指定参数的日期对象
	     *  @param dateStr 时间字符串
	     *  @param format 格式
	     *  @return
	  	 *  Date
	  	 *  @author   lee
	     *  @version    2013-2-28 
	  	 */
	public static Date getDate(String dateStr, String format1) {
 		try {
 			if(format1==null){
 				format1=format;
 			}
			return new SimpleDateFormat(format1).parse(dateStr);
		} catch (Exception e) {
			return new Date();
		}
		
	}

}

