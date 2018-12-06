package codesprinters.autojava;

public abstract class AbstractShape implements Shape {

	//@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

}
