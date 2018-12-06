package codesprinters.autojava;

public class Circle extends AbstractShape{

	public double getR() {
		return r;
	}

	private double r;
	
	public Circle (double r) {
		this.r = r;
	}

	//@Override
	public double getArea() {
		return Math.PI * r * r;
	}

}
