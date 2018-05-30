package com.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

	
	public static void showParams(HttpServletRequest request) {
        Map map = new HashMap();
        if(null==request){
        	return ;
        }
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();

            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    map.put(paramName, paramValue);
                }
            }
        }

        Set<Map.Entry<String, String>> set = map.entrySet();
        System.out.println("--------------params----------------");
        for (Map.Entry entry : set) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
	
	
	public static void showUrlStringParams(List<String> list) {

        System.out.println("--------------UrlParams----------------");
        for (String params : list) {
            System.out.println(params);
        }
    }
	
	
	
	private static final String SN_KEY="sn";
	private static final String FIRMCODE_KEY="firmCode";
	private static final String TIMESTAMP_KEY="timestamp";
	private static final String OID_KEY="oid"; 
	
	public static void validateSign(HttpServletRequest request,String firmCode,String sn){
		SortedMap<String, String> signedMap = new TreeMap<String, String>();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();

            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                	signedMap.put(paramName, paramValue);
                }
            }
        }
        signedMap.put(SN_KEY, sn);
        signedMap.put(FIRMCODE_KEY, firmCode);
        signedMap.put(TIMESTAMP_KEY, request.getHeader("x-mtms-date"));
        signedMap.put(OID_KEY, request.getHeader("x-mtms-oid"));
		
		StringBuilder sb = new StringBuilder();
		for(String key:signedMap.keySet()){
			sb.append(key+"="+signedMap.get(key) + "&");
		}
		String signStr = "";
		if(sb.length() > 0){
			signStr = sb.substring(0,sb.length() - 1);
		}
		System.out.println("-------------validateSign-----------------");
		System.out.println(signStr);
	}
	
	
	
	public static void showHeaders(HttpServletRequest request) {
        Map map = new HashMap();
        if(null==request){
        	return ;
        }
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();

            String headerValue = request.getHeader(headerName);
            map.put(headerName, headerValue);
        }

        Set<Map.Entry<String, String>> set = map.entrySet();
        System.out.println("-------------headers-----------------");
        for (Map.Entry entry : set) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
