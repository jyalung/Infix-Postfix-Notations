package project2;

public class StackOverflowException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	StackOverflowException(){
		super("Stack is full.");
	}
}
