package com.hexa.generics;

public class GenericsImpl {
	public static void main(String[] args) {
		GenericExample<String> obj = new GenericExample<String>("Hello");
		System.out.println(obj.getX());
	}
}
