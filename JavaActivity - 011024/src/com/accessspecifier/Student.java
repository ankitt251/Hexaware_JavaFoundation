package com.accessspecifier;

public class Student{
	public String name;
	public String color;
	
	public void display(String name, String color) {
		System.out.println("Name of the student is: "+ name);
		System.out.println("Color: " + color);
	}
}

class ChildClass extends Protected{
	void test() {
		display();
	}
}

class Protected {
	protected String name = "Protected Class";
	
	protected void display() {
		System.out.println("Name: "+ name);
	}
}

class DefaultClass{
	String name = "Default";
	void display() {
		
	}
}

class Main {
	public static void main(String[] args) {
		Student s1 = new Student();
		s1.display("Ankit", "Green");
		
		ChildClass c1 = new ChildClass();
		c1.display();
	}
}
