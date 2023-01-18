//Expansion of code written by Thomas Conner and Fatima Khalid
//Class Node that makes up the Stack data structure

package com.mycompany.main;

public class Node{
    
    //Holds two member variables, character and a 
    //reference to the next node in the stack
    final private char character;
    private Node next;
    
    //Constructor for Node object
    public Node(char c){
        character = c;
    }
    
    //Returns the character stored in the Node object
    public char getCharacter(){
        return character;
    }
    
    //Returns the next Node in the stack
    public Node getNext(){
        return next;
    }
    
    //Sets the next Node in the stack
    public void setNext(Node n ){
        next = n;
    }
}
