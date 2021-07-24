/**
 * Name: Autumn Arnold
 * Date: 4/29/2020
 * Project: Lisp Expression Evaluator
 */

package evaluator;


public class LinkedStack<T> implements StackInterface<T> {
	private Node<T> topNode;	// references the first node in chain
	private int count;			// number of nodes in the stack
	
	// default constructor
	public LinkedStack() {
		topNode = null;
		count = 0;
	}
	
	@Override
	public int size() {
		return count;
	}

	@Override
	public void push(T aData) {
		Node<T> newNode = new Node<T>(aData, topNode);
		topNode = newNode;
		count++;
	}

	@Override
	public T pop() {
		T top = peek();
		
		if(topNode != null) {
			topNode = topNode.getNextNode();
			count--;
		}
		
		return top;
	}

	@Override
	public T peek() {
		T top = null;
		
		if(topNode != null) {
			top = topNode.getData();
		}
		
		return top;
	}

	@Override
	public boolean empty() {
		return topNode == null;
	}

	@Override
	public void clear() {
		topNode = null;
		count = 0;
	}

	@Override
	public String toString() {
		String result = "[";
		Node<T> currentNode = topNode;
		
		while(currentNode != null) {
			result = result + currentNode.getData() + ",";
			currentNode = currentNode.getNextNode();
		}
		
		result = result + "]";
		return result;
	}
}
