//This is an expansion of a program originially 
//written by Fatima Khalid and Thomas Conner with
//the goal of increased performance and better
//documentation

//The original version of this code was written as part
//of an in-class assignment for a Computer Science II
//class at the University of Texas at Dallas on October 5, 2022

//Link to unedited, original project: 
//https://github.com/ThomasDanger/InfixToPostfix-unedited

//This program utilizes a specialized stack datastructure 
//to convert an infix expression to a postfix expression
//In the current build, this will only convert an infix
//expression to a postfix expression. In the near future, 
//I hope to create a program that will convert to prefix
//and evaluate these expressions if requested

package com.mycompany.main;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

//Main function
public class Main
{
    public static void main(String[] args) throws IOException{
        
        //Creates Scanner to scan std
        Scanner scanstd = new Scanner(System.in);
        
        //Prompt user for input file name and store it
        System.out.print("Enter input file: ");
        String inputFileName = scanstd.next();
        
        //Opens the input file to be read-in
	File inputFile = new File(inputFileName);
        
        if(inputFile.exists() && inputFile.canRead()){
            //Creates a scanner to scan the input file
            Scanner scanFile = new Scanner (inputFile);

            //Prompt user for output file name and store it
            System.out.print("Enter output file: ");
            String outputFileName = scanstd.next();

            //Opens a file write to write to the output file
            FileWriter myWriter = new FileWriter(outputFileName);

            //Creates new Stack
            Stack stack = new Stack();

            //While there is still content in the input file...
            while(scanFile.hasNext()){

                //Creates string to hold the postfix expression 
                //that will be built upon
                String postfix = "";

                //Creates a string to hold the infix expression 
                //read in from the input file
                String infix = scanFile.nextLine();

                //Increment once for every characer in the infix expression
                for(int i = 0; i< infix.length(); i++){

                    //If the read-in character is a digit, append the digit to postfix
                    if(Character.isLetterOrDigit(infix.charAt(i))){
                        postfix += (infix.charAt(i));
                    }

                    //Otherwise, if the exponentiation operator is found...
                    else if(infix.charAt(i) == '^'){
                        //Until the end of stack is reached or an operator of lower precedence
                        //is found, pop and append
                        while(stack.top()!= null && stack.peek() == '^')
                        {
                            Node removed = stack.pop();
                            postfix+=(removed.getCharacter());
                        }
                        //After operator of lower precedence is reached or stack is empty,
                        //push the exponentiation operator
                        stack.push(new Node(infix.charAt(i)));
                    }

                    //Otherwise, if the multiplication or division operator is found...
                    else if(infix.charAt(i) == '*' || infix.charAt(i) == '/'){

                        //Until the end of stack is reached or an operator of lower precedence
                        //is found, pop and append
                        while(stack.top()!= null && (stack.peek() == '*' || stack.peek() == '/'|| stack.peek() == '^'))
                        {
                            Node removed = stack.pop();
                            postfix+=(removed.getCharacter());
                        }
                        //After operator of lower precedence is reached or stack is empty,
                        //push the multiplication/division operator
                        stack.push(new Node(infix.charAt(i)));
                    }

                    //Otherwise, if the addition or subtraction operator is found...
                    else if(infix.charAt(i) == '+' || infix.charAt(i) == '-'){
                        //Until the end of stack is reached or an operator of lower precedence
                        //is found, pop and append
                        while(stack.top()!= null && stack.peek() != '(' )
                        {
                            Node removed = stack.pop();
                            postfix+=(removed.getCharacter());
                        }
                        //After operator of lower precedence is reached or stack is empty,
                        //push the multiplication/division operator
                        stack.push(new Node(infix.charAt(i)));
                    }

                    //Otherwise, if the left parenthesis is found, push
                    else if(infix.charAt(i) == '('){
                        stack.push(new Node(infix.charAt(i)));
                    }

                    //Otherwise, if right parenthesis is found pop until left parenthesis is found
                    else if(infix.charAt(i) == ')'){
                        while(stack.peek() != '(' )
                        {
                            Node removed = stack.pop();
                            postfix+=(removed.getCharacter());
                        }
                        stack.pop();
                    }
                }

                //when end of input reached, pop stack until empty and append to postfix
                while(stack.top()!= null){
                    Node removed = stack.pop();
                    postfix+=(removed.getCharacter());
                }

                //Write postfix to newline
                myWriter.write(postfix + "\n");
            }
            //Closes file scanner and printwriter
            scanFile.close(); 
            myWriter.close();   
        }
        else
            System.out.println("File not found");
        //Close System.in scanner
        scanstd.close();
    }
}
