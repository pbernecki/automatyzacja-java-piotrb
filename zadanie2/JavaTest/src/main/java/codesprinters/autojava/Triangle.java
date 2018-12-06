package codesprinters.autojava;

public class Triangle extends AbstractShape {
	
	private double a;
	private double b;
	private double c;
	
	public Triangle (double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	//@Override
	public double getArea() {
		double p = 0.5d * (a + b + c);
		return Math.sqrt(p * (p-a) * (p-b) * (p-c));
	}

	public double getA() {
		return a;
	}

	public double getB() { return b; }

	public double getC() {
		return c;
	}
}
