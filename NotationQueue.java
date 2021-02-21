package project2;
import java.util.ArrayList;

public class NotationQueue<T> implements QueueInterface<T> {


	private ArrayList<T> queue;
	private int size;

	NotationQueue() {
		queue = new ArrayList<>();
		size = 10;
	}

	NotationQueue(int size) {
		queue = new ArrayList<>(size);
		this.size = size;
	}

	@Override
	public boolean isEmpty() {
		if (queue.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isFull() {

		if (queue.size() == size) {
			return true;
		}
		return false;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {

		if (isEmpty()) {
			throw new QueueUnderflowException();
		}

		T next = queue.get(0);
		queue.remove(0);
		queue.trimToSize();
		queue.ensureCapacity(size);

		return next;
	}

	@Override
	public int size() {

		return queue.size();
	}

	@Override
	public boolean enqueue(T e) throws QueueOverflowException {

		if (queue.size() == size) {
			throw new QueueOverflowException();
		}

		queue.add(e);
		return true;
	}
	
	@Override
	public String toString() {
		String string = "";
		
		for(T item: queue) {
			string += item.toString(); 
		}
		return string;
	}

	@Override
	public String toString(String d) {
		String string = "";
		
		for(T item: queue) {
			string += item.toString();
			string += d;
		}
		
		string = string.substring(0,string.length()-1);
		
		return string;
	}

	@Override
	public void fill(ArrayList<T> list) {
		
		queue.clear();
		
		for (T item : list) {
			queue.add(item);
		}

	}

}
