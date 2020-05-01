/*
 * Name: Autumn Arnold
 * Date: 4/29/2020
 * Project: Adding a GUI to an old SF State Project
 */
package lispexpressionevaluator;

/**
 *
 * @author Autumn
 */
public class Node<T> {
    private T       data; // entry in stack
    private Node<T> next; // link to next node
    
    Node(T aData) {
        this(aData, null);
    } // end constructor
    
    Node(T aData, Node<T> nextNode){
        data = aData;
        next = nextNode;
    } // end constructor
    
    T getData(){
        return data;
    } // end getData
      
    void setData(T aData){
        data = aData;
    } // end setData
      
    Node<T> getNextNode(){
        return next;
    } // end getNextNode
      
    void setNextNode(Node<T> nextNode){
        next = nextNode;
    } // end setNextNode
}
