package com.hexaware.collections;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {
	public static void main(String[] args) {
		Map<Integer, Integer> m = new HashMap<>();
		
		m.put(101, 500);
		m.put(102, 600);
		m.put(103, 700);
		m.put(104, 800);
		m.put(105, 900);
		
		System.out.println(m.size());
		
		for(Map.Entry<Integer, Integer> obj : m.entrySet()) {
			System.out.println("Key : "+obj.getKey() + " Values are : " + obj.getValue());
		}
	}
}
