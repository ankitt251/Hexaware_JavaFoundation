package com.hexa.generics;

public class GenericExample<T extends Object> {
	T x;
	
	public GenericExample(T x) {
		this.x = x;
	}
	
	public T getX() {
		return x;
	}
	public void setX(T x) {
		this.x = x;
	}
}
