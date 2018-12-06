package codesprinters.autojava;

public class Rectangle extends AbstractShape {
	
	private double a;
	private double b;
	
	public Rectangle(double a, double b) {
		this.a = a;
		this.b = b;
	}

	//@Override
	public double getArea() {
		return a*b;
	}

	public double getA() {
		return a;
	}

	public double getB() {
		return b;
	}
}
