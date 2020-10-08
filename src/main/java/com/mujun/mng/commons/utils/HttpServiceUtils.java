package com.mujun.mng.commons.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * HTTP 请求工具类
 * @author Administrator
 *
 *
 */
public class HttpServiceUtils {


	private static final int PAGE_NO = 1;

	private static final int PAGE_SIZE = 10;

	public static Map<String, Object> getParamsFromReq(HttpServletRequest req) throws IOException,
			UnsupportedEncodingException {

		Map<String, Object> paramsMap = new HashMap<String, Object>();

		InputStream is;
		String params = req.getQueryString();
		is = req.getInputStream();

		paramsMap.putAll(HttpServiceUtils.getUrlParamValue(params));

		StringBuffer sb = new StringBuffer() ;
		InputStreamReader isr = new InputStreamReader(is,"UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String s = "" ;
		while((s=br.readLine())!=null){
			sb.append(s) ;
		}
		String paramStr =sb.toString();
		if(req.getContentType()!=null&&req.getContentType().equals("application/xml")){
			paramsMap.put("xml",paramStr);
			return  paramsMap;
		}
		if(!"".equals(paramStr)){
				JSONObject jo = JSONObject.parseObject(paramStr);
				paramsMap.putAll(HttpServiceUtils.parseJsonPostParameters(jo));

		}

		return paramsMap;
	}

	public static Map<String, Object> getParams2Map(HttpServletRequest req) throws IOException,
			UnsupportedEncodingException {

		Map<String, Object> paramsMap = new HashMap<String, Object>();

		InputStream is;
		String params = req.getQueryString();
		is = req.getInputStream();


		StringBuffer sb = new StringBuffer() ;
		InputStreamReader isr = new InputStreamReader(is,"UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String s = "" ;
		while((s=br.readLine())!=null){
			sb.append(s) ;
		}
		String paramStr =sb.toString();
		if(req.getContentType()!=null&&req.getContentType().equals("application/xml")){
			paramsMap.put("xml",paramStr);
			return  paramsMap;
		}
		if(!"".equals(paramStr)){
				paramsMap.putAll(HttpServiceUtils.getUrlParamValue(paramStr));

		}

		return paramsMap;
	}
	/**
	 * 提取查询参数构造成map
	 * @param urlParams
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static Map<String, Object> getUrlParamValue(String urlParams) throws UnsupportedEncodingException{
		// publicCode=abcd&type=yx
		if(urlParams != null){
			urlParams = URLDecoder.decode(urlParams,"utf-8");
		}
		Map<String, Object> values = new HashMap<String, Object>();
		if(urlParams != null && !"".equals(urlParams)){
			String[] params = urlParams.split("&");
			for(String param : params){
				String key = param.split("=")[0];
				String value = param.split("=")[1];
				if(value.startsWith("[")){
					value = value.replace("[", "");
					value = value.replace("]", "");
					String[] list = value.split(",");
					List<String> valueList = new ArrayList<String>();
					for(String str : list){
						valueList.add(str);
					}
					values.put(key, valueList);
				} else {
					values.put(key, value);
				}

			}
		}
		return values;
	}

	public static Map<String, Object> parsePostParameters(Map<String, String[]> source){
		Map<String, Object> dest = new HashMap<String, Object>();
		for (String key : source.keySet()) {
		   dest.put(key, source.get(key)[0]);
		}

		return dest;
	}


	@SuppressWarnings("unchecked")
	public static Map<String, Object> parseJsonPostParameters(JSONObject jo){
		Map<String, Object> dest = new HashMap<String, Object>(jo);
		return dest;
	}

	/** 首字母转大写
	 * @param str 源字符串
	 * @return 转大写后
	 */
	public static String firstCharacterToUpper(String str){

		String newStr=str.substring(0, 1).toUpperCase()+str.replaceFirst("\\w","");
		return newStr;
	}

}
