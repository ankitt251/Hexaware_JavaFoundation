package com.strings;

public class StringExample {
	public static void main(String[] args) {
		String s1 = "hello";
		String s2 = "hello";
		
		String s3 = new String("hello");
		s1.concat(s2);
		System.out.println(s1);
		/*
		 * s1 = s1.concat(s2); System.out.println(s1);
		 */
		
		StringBuffer s4 = new StringBuffer("hello");
		s4.append(" World");
		System.out.println("Value of s4: " + s4);
		
		StringBuilder s5 = new StringBuilder("hello");
		s5.append(" World");
		System.out.println("Value of s5: " + s5);
		s5.replace(0, 1, s3);
		System.out.println("Value of s5: " + s5);
		
		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));
		System.out.println(s1 == s3);
		System.out.println(s1.equals(s3));
		
	}
}
