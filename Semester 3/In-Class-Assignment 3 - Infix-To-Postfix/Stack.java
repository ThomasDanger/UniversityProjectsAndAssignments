//Expansion of code written by Thomas Conner and Fatima Khalid
//for Computer Science II at The University of Texas at Dallas

package com.mycompany.main;

//Class for Stack data structure
public class Stack{

    //Node head is the only member variable
    private Node top;
    
    //Default Constructor
    public Stack(){
        top = null;
    }
    
    //Constructor that accepts a node h as an argument
    public Stack(Node top_){
        top = top_;
    }
    
    //Returns the value of the top of stack
    public char peek(){
        return top.getCharacter();
    }
    
    //Returns referece to top of stack
    public Node top(){
        return top;
    }
    
    //Adds a new Node at the head of the stack, pushed other nodes back
    public void push(Node newTop){
        newTop.setNext(top);
        top = newTop;
    }
    
    //Removes the element at the head of the stack, returns this head
    public Node pop(){
        Node temp = top;
        top = top.getNext();
        return temp;
    }
}
