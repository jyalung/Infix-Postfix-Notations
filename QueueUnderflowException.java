package project2;

public class QueueUnderflowException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	QueueUnderflowException(){
		super("Queue is empty.");
	}
}
