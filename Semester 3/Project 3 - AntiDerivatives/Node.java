//Thomas Conner

//This is a generic node class used with a binary search tree class

package com.mycompany.main;

public class Node<G extends Comparable<G>> implements Comparable<Node<G>> {
    private G data;
    private Node<G> left;
    private Node<G> right;
    
    //Node default constructor
    public Node(){
        data = null;
        left = null;
        right = null;
    }
    
    //Node constructor with generic object as parameter
    public Node(G data_){
        data = data_;
        left = null;
        right = null;
    }
    
    //data accessor
    public G getData(){
        return data;
    }
    
    //left node accessor
    public Node<G> getLeft(){
        return left;
    }
    
    //right node accessor
    public Node<G> getRight(){
        return right;
    }
    
    //data mutator
    public void setData(G data_){
        data = data_;
    }
    
    //left node mutator
    public void setLeft(Node<G> left_){
        left = left_;
    }
    
    //right node mutator
    public void setRight(Node<G> right_){
        right = right_;
    }
    
    //Overriden compareTo compares data in node
    @Override
    public int compareTo(Node<G> n){
        return data.compareTo(n.getData());
    }
    
    //Overridden toString returns the data's toString
    @Override
    public String toString(){
        return data.toString();
    }
}
