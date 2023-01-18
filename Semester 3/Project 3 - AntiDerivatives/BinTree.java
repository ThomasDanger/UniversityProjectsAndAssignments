//Thomas Conner
//tsc200002

//This is a generic binary search tree class

package com.mycompany.main;

public class BinTree<G extends Comparable<G>> {
    private Node<G> root;
    
    //Default constructor
    public BinTree(){
        root = null;
    }
    
    //Another constructor (not used in program)
    public BinTree(Node<G> root_){
        root = root_;
    }

    //Inserts new nodes into binary search tree given the generic object
    public void insert(G generic){
        Node<G> target = new Node<G>(generic);
        if(root == null)
            root = target;
        else
            insert(target, root);
    }
    
    //Recursive Helper function for insert()
    private void insert(Node<G> target, Node<G> current){
        if(current != null){
            //If target is less than current node...
            if(target.compareTo(current) < 0){
                //If left node is null, insert at left node
                if(current.getLeft() == null)
                    current.setLeft(target);
                //else do recursion w/ left node
                else
                    insert(target, current.getLeft());
            }
            //If target is equal to current node...
            else if(target.compareTo(current) == 0){
                //If left node is null, put target at left node
                if(current.getLeft() == null)
                    current.setLeft(target);
                //Else, add current's left to the target and set curren't left to target
                else{
                    target.setLeft(current.getLeft());
                    current.setLeft(target);
                }
            }
            //If target is greater than current...
            else if(target.compareTo(current) > 0){
                //If right node is null, insert target at right
                if(current.getRight() == null)
                    current.setRight(target);
                //else do recursion with right node
                else
                    insert(target, current.getRight());
            }
        }
    }
    
    //Used for remove function
    private void insert(Node<G> target){
        if(root == null)
            root = target;
        else
            insert(target, root);
    }
    
    //Search function that returns a target node if found and null if not
    public Node<G> search(G target){
        return search(target, root);
    }
    
    //Recursive helper function for search
    private Node<G> search(G target, Node<G> current){
        Node<G> returnVal = null;
        //If current node is not null...
        if(current != null){
            //If target is equal to the data at the current node, return current node
            if((target).compareTo(current.getData()) == 0){
                returnVal = current;
            }
            //If target is less than data at current node, do recursion w left node 
            else if(target.compareTo(current.getData()) < 0){
                if(current.getLeft() != null)
                    returnVal = search(target, current.getLeft());
            }
            //If target is greater than data at current node, do recursion w right node
            else if(target.compareTo(current.getData()) > 0){
                if(current.getRight() != null)
                    returnVal = search(target, current.getRight());
            }
        }
        return returnVal;
    }
    
    //Removes a node with the given target
    public Node<G> remove(G target){
        if(root != null)
            return remove(target, root);
        else 
            return null;
    }
    
    //Recursive helper function for remove
    public Node<G> remove(G target, Node<G> parent){
        
        //insert (former) root's left
        if(target.compareTo(parent.getData()) < 0){
            //Current node equals left
            Node<G> cur = parent.getLeft();
            //If it's null at the current node, return null
            if (cur == null) 
                return null;
            //If target is equal to the data at the current node...
            else if(target.compareTo(cur.getData())==0){
                Node<G> temp = cur;
                Node<G> tempLeft = cur.getLeft();
                Node<G> tempRight = cur.getRight();
                //Set parent's left equal to null (removing current node)
                parent.setLeft(null);
                //If current's left was not null, insert current's left back into tree
                if(tempLeft != null)
                    this.insert(tempLeft);
                //If current's right was not null, insert current's right back into tree
                if(tempRight != null)
                    this.insert(tempRight);
                return temp;
            }
            //Otherwise, do recursin with current node
            else
                return remove(target, cur);
        }
        //If the target data is greater than parent node's data
        else if(target.compareTo(parent.getData()) > 0 ){
            //current node equals right
            Node<G> cur = parent.getRight();
            //if current node is null, return null
            if (cur == null) 
                return null;
            //If target is equal to current node's data
            else if(target.compareTo(cur.getData())==0){
                Node<G> temp = cur;
                Node<G> tempLeft = cur.getLeft();
                Node<G> tempRight = cur.getRight();
                //Remove current node by setting parent right to null
                parent.setRight(null);
                //Re-insert current's left node
                if(tempLeft != null)
                    this.insert(tempLeft);
                //Re-insert current's right node
                if(tempRight != null)
                    this.insert(tempRight);
                return temp;
            }
            else
                return remove(target, cur);
        }
        //Otherwise, root must be equal, remove root
        else{
            Node<G> temp = root;
            Node<G> tempLeft = root.getLeft();
            Node<G> tempRight = root.getRight();
            root = null;
            //insert (former) root's left
            if(tempLeft != null)
                this.insert(tempLeft);
            //insert (former) root's right
            if(tempRight != null)
                this.insert(tempRight);
            return temp;
        }
    }
}
