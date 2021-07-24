/**
 * Name: Autumn Arnold
 * Date: 4/29/2020
 * Project: Lisp Expression Evaluator
 */

package evaluator;

public class Node<T> {
	private T data;			// entry in the stack
	private Node<T> next;	// link to the next node
	
	// default constructor
	Node(T aData) {
		this(aData, null);
	}
	
	// alternate constructor
	Node(T aData, Node<T> nextNode) {
		data = aData;
		next = nextNode;
	}
	
	// accessors and mutators
	T getData() {
		return data;
	}
	
	void setData(T aData) {
		data = aData;
	}
	
	Node<T> getNextNode() {
		return next;
	}
	
	void setNextNode(Node<T> nextNode) {
		next = nextNode;
	}
}
