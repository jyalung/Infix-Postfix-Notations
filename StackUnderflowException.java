package project2;

public class StackUnderflowException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	StackUnderflowException(){
		super("Stack is empty");
	}
}
