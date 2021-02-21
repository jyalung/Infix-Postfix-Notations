package project2;

public class QueueOverflowException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	QueueOverflowException(){
		super("Queue is full.");
		
	}
}
