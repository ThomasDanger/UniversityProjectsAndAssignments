//Thomas Conner

//This is a program that uses a binary search tree to find the antderivative of a simple integral

package com.mycompany.main;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        
        //Scanner scan = new Scanner(System.in);
        System.out.print("Enter file name: \n");
        //String fileName = scan.next();
        
        //Inside of try block in case FileNotFoundException is thrown
        try{
            //Create scanner for user-entered file
            Scanner scanFile = new Scanner(new File("sample_integrals.txt"));
            
            //While there is unread content in the file...
            while(scanFile.hasNext()){
                
                //Create int array to hold the bounds
                int[] bounds = new int[2];
                
                //Create new binary tree
                BinTree<Term> tree = new BinTree<Term>();
                
                //Set bounds and populate tree using pareEquation()
                bounds = parseEquation(scanFile.nextLine(), tree);
                
                //Print the antiderivative
                printAntiDerivative(bounds, tree);
            }
        }
        //If FileNotFoundException is thrown, output error
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
    
    //function parseEquation takes a equation String in A|B Cx^D + Ex^F - G ... dx format
    //and populates the given binary tree with the terms of the equation
    public static int[] parseEquation(String eqStr, BinTree<Term> equation){
        
        //This holds the index the function will start reading the terms at
        int startingIndex = 1;
        
        //FIND BOUNDS-----------------------------------------------------------
        
        int[] bounds = {0,0};
        
        //If the first character of the equation is not a pipe, find bounds
        if(eqStr.charAt(0) !='|'){
            
            //FIND LOWER BOUND
            String str = "";
            for(int i = 0; i < eqStr.length(); i++){
                if(eqStr.charAt(i) == '|'){
                    startingIndex = i+1;
                    break;
                }
                else
                    str+=eqStr.charAt(i);
            }
            bounds[0] = Integer.parseInt(str);
            
            //FIND LOWER BOUND
            str = "";
            for(int i = startingIndex; i < eqStr.length(); i++){
                if(eqStr.charAt(i) == ' '){
                    startingIndex = i+1;
                    break;
                }
                else
                    str+=eqStr.charAt(i);
            }
            bounds[1] = Integer.parseInt(str);
        }
        
        //FIND TERMS
        //for (int i = starting index to eqStr's length -2)
        for(int i = startingIndex; i < eqStr.length()-2; i++){
            //Holds the multiplier for each term (ex. - -4x would become +4x)
            //The coefficient will be multiplied by this when creating terms
            int multiplier = 1;
            
            //If the character at i is not a space...
            if(eqStr.charAt(i) != ' '){
                
                //Hold the String representations of the coefficient and exponent respectfully
                String coefStr = "";
                String expStr = "";
                
                //if there is a -, set multiplier to -1
                if(eqStr.charAt(i) == '-'){
                    multiplier = -1;
                    i++;
                }
                //If there is a +, skip
                else if(eqStr.charAt(i) == '+'){
                    i++;
                }
                //while a character that would end a coefficient is not found...
                while(eqStr.charAt(i) != 'x' && eqStr.charAt(i) != '+' && eqStr.charAt(i) != '-' && eqStr.charAt(i) != 'd'){
                    if(eqStr.charAt(i) != ' '){
                        coefStr+=eqStr.charAt(i);
                    }
                    i++;
                }
                //if x is found...
                if(eqStr.charAt(i)=='x'){
                    //If there was not explicit coefficient, coefficient = 1
                    if(coefStr.compareTo("")==0){
                        coefStr += "1";
                    }
                    //Skip
                    i+=1;
                    
                    //If there is a carrot (exponentiation)...
                    if(eqStr.charAt(i) == '^'){
                        //skip
                        i+=1;
                        //If the exponent is negative, add a -
                        if(eqStr.charAt(i)=='-'){
                            expStr+='-';
                            i+=1;
                        }
                        //While a character that would end an exponent is not found keep appending to expStr
                        while(eqStr.charAt(i) != '+' && eqStr.charAt(i) != '-' && eqStr.charAt(i) != ' ' && eqStr.charAt(i) != 'd'){
                            expStr+=eqStr.charAt(i);
                            i++;
                        }
                    }
                    //If no explicit exponent, set exponent to 1
                    else{
                        expStr="1";
                        i++;
                    }
                }
                //If no x, set exponent to 0
                else{
                    expStr="0";
                    i++;
                }
                    
                if(coefStr.compareTo("") != 0){
                    //Create term (coefficient)x^exponent
                    //System.out.println(multiplier);
                    int coefficient = multiplier*Integer.parseInt(coefStr);
                    int exponent = Integer.parseInt(expStr);
                    equation.insert(new Term(coefficient,exponent));
                    //System.out.println("Term: "+coefficient+"x^"+exponent+"added");
                    if(exponent==1 || exponent == 0)
                        i--;
                }
                i--;
            }
        }
        return bounds;
    }
    
    //Prints the antiderivative. 
    //(If bounds are 0,0 it will print indefinite and if bounds are not 0,0 it prints definite
    public static void printAntiDerivative(int[] bounds, BinTree<Term> equation){
        
        //flag for if the first term has already been printed
        //This avoids the plus in + 2x, 1|2 = 2
        //Instead prints            2x, 1|2 = 2
        boolean moreThanOne = false;
        
        //This holds the solution to the integral (only prints if integral is definite)
        double solution = 0;
        
        //Holds the string that will be used to build thestring representation of the term
        String str = "";
        
        //For int exponent = 10 to -10...
        for(int exponent = 10; exponent >= -10; exponent--){
            
            //If the exponent (10 to -10) exists in the tree...
            if(equation.search(new Term(0,exponent)) != null){
                //Set coefficient to zero (needs to be zero so coefficients can be added
                //if there are duplicates
                int coefficient = 0;

                //while the exponent still exists in the tree (in case there are duplicates), add coefficients
                while(equation.search(new Term(0,exponent)) != null){
                    Term t = equation.remove(new Term(0,exponent)).getData();
                    coefficient += t.getCoefficient();
                }
                
                //add the solution of the term to the variable solution
                solution+=(((double)coefficient/(double)(exponent+1))*Math.pow(bounds[1], exponent+1)) - ((double)coefficient/(double)(exponent+1))*Math.pow(bounds[0], exponent+1);
                
                //If this is not the first term, add a + or a - operator
                if(moreThanOne){
                    if((double)coefficient/(double)(exponent+1)>0){
                        str+=" + ";
                    }
                    else if((double)coefficient/(double)(exponent+1)<0){
                        str+=" - ";
                    }
                }
                //If the coefficient is not 0 and the coefficient is not 1 and the exponent is 0, add the coefficient to str
                //Avoids 0x^1 and 1x^1
                if(Math.abs(coefficient/((double)exponent+1)) == 1.0){
                    str+="";
                    //set flag so that more than one is equal to true
                    moreThanOne = true;
                }
                else if(coefficient!= 0 && exponent == 0 && coefficient !=1){
                    if(!moreThanOne && coefficient<0)
                        str+="-";
                    str+=Math.abs(coefficient);
                    //set flag so that more than one is equal to true
                    moreThanOne = true;
                }
                //else if there is an irreduceable fraction, put in faction form
                else if(coefficient != 0 && exponent!=-1 && Math.abs((double)coefficient/(double)(exponent+1))!=1.0 && !(coefficient%(exponent+1)==0)){
                    int gcd=getGCD(coefficient, exponent+1);
                    str+= "(";
                    if(!moreThanOne && (double)coefficient/(double)(exponent+1)<0)
                        str+="-";
                    str+=Math.abs(coefficient/gcd)+"/"+Math.abs((exponent+1)/gcd)+")";
                    //set flag so that more than one is equal to true
                    moreThanOne = true;
                }
                //else divide the coefficient by exponent+1
                else{
                    if(coefficient != 0 && !moreThanOne && ((double)coefficient/((double)exponent+1))<0)
                        str+="-";
                    if(coefficient!=0){
                        str+= Math.abs(coefficient/(exponent+1));
                        //set flag so that more than one is equal to true
                        moreThanOne = true;
                    }
                }
                //If neither the ceofficient or exponent+1 is equal to zero, ads x to str
                if(coefficient != 0 && exponent+1 != 0)
                    str+= "x";
                //If the exponent is not equal to 1 or 0, add carrot and exponent+1 to str
                if(coefficient != 0 && exponent+1 != 1 && exponent+1 != 0)
                    str+= "^"+(exponent+1);
                
            }
        }
        if(str.length() == 0){
            str+="0";
        }
        //Print the equation
        System.out.print(str);
                
        //If the bounds are 0, 0 finish with + C (indefinite)
        if(bounds[0] == 0 && bounds[1] == 0)
            System.out.println(" + C");
        //If the bounds are not 0, 0 finish with solution (definite)
        else{
            System.out.printf(", %d|%d = %.3f\n",bounds[0], bounds[1], solution);
        }
    }
    
    //Function calculates the greatest common divisor between twonumbers using Euclidian algorithm
    public static int getGCD(int num1, int num2){
        int remainder = num1%num2;
        if(remainder == 0)
            return num2;
        else
            return getGCD(num2, remainder);
    }
}
