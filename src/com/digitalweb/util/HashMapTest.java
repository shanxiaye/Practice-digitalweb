package com.digitalweb.util;
import java.util.*;
public class HashMapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HashMap map = new HashMap();
		map.put("1001","杰克");
		map.put("1002","汤姆");
		map.put("1003","露西");
		System.out.println(map.isEmpty());
		System.out.println(map.containsKey("1001"));
		System.out.println(map.containsValue("露西"));
		System.out.println(map.get("1002"));
		System.out.println(map.entrySet());
		System.out.println(map.keySet());
		
//		Iterator iter = map.entrySet().iterator();
//		while(iter.hasNext()){
//			Map.Entry entry = (Map.Entry) iter.next();
//			System.out.println(entry.getKey()+"-"+entry.getValue());
//		}
		Iterator iter = map.keySet().iterator();
		while(iter.hasNext()){
			String key = (String)iter.next();
			System.out.println(key +"-"+map.get(key));
		}
		
	}

}
