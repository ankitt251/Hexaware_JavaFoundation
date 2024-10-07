package com.hexaware.Map;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentMapDemo {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Map<String, Integer> m = new HashMap<>();
		System.out.print("Enter name: ");
		String name = scan.nextLine();
		System.out.print("Enter roll number: ");
		int roll = scan.nextInt();
		scan.nextLine();
		m.put(name, roll);
		//System.out.println(m);
		
		for(Map.Entry<String, Integer> obj : m.entrySet()) {
			System.out.println("Name : "+obj.getKey() + " Roll Number : " + obj.getValue());
		}
		scan.close();
	}
}
