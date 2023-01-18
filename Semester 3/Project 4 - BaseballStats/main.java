//Thomas Conner

package com.mycompany.main;

import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;

//Main function
public class Main {
    public static void main(String[] args) {
        //Declare scanner to scan console
        Scanner scan = new Scanner(System.in);
        
        //Create Hashmaps for the keyfile, the away players, and the home players 
        HashMap<String, Character> hashKey = new HashMap<String, Character>();
        HashMap<String, Player> hashAway = new HashMap<String, Player>();
        HashMap<String, Player> hashHome = new HashMap<String, Player>();
        
        //Create arraylists for home players, away players, and a list that represents the unionof home and away players
        ArrayList<Player> homePlayersList = new ArrayList<Player>();
        ArrayList<Player> awayPlayersList = new ArrayList<Player>();
        ArrayList<Player> superList = new ArrayList<Player>();
        
        //Try get the key from a file into a hashtable (Scanning files throws FileNotFoundException
        //So must be in try block
        try{
            getKey(hashKey);
        }
        catch(FileNotFoundException e){
            System.out.println("ERROR: keyfile not found");
        }
        
        //Get the name of the file containing player stats
        String filename = scan.next();
        
        //Try to populate player hashTables with the stats from the file
        //Once again, scanning files throws exception => must be in try block
        try{
            getStats(filename, hashKey, hashAway, hashHome);
        }
        catch(FileNotFoundException e){
            System.out.println("Could not find file \""+filename+"\"");
        }
        
        
        //Add every Player from the hashTables to the respective arrayLists so they can sort the players
        //Alphabetically
        for(Player p : hashAway.values()){
            awayPlayersList.add(p);
        }
        for(Player p : hashHome.values()){
            homePlayersList.add(p);
        }
        
        //Sort Player arraylists using the ArrayList's built-in sort function
        awayPlayersList.sort(Comparator.naturalOrder());
        homePlayersList.sort(Comparator.naturalOrder());
        
        //Print away team stats
        System.out.println("\nAWAY");
        for(Player p : awayPlayersList){
            System.out.println(p);
        }
        //Print home team stats
        System.out.println("\nHOME");
        for(Player p : homePlayersList){
            System.out.println(p);
        }
        
        //Add all players from away to super list
        for(Player p : awayPlayersList){
            superList.add(p);
        }
        //Add all players from home to super list
        for(Player p : homePlayersList){
            superList.add(p);
        }
        
        //Print the leaders from the super list
        printLeaders(superList);
        
        //close scanner to prevent leakage
        scan.close();
    }
    
    //Parses stats file to ge player's stats
    public static void getStats(String filename, HashMap<String, Character> hashKey, HashMap<String, Player> hashAway, HashMap<String, Player> hashHome) throws FileNotFoundException{
        File inputFile = new File(filename);
        Scanner scan = new Scanner(inputFile);
        
        //While there are more stats to parse...
        while(scan.hasNext()){
            
            //Store the info on the line of the file
            char homeOrAway = scan.next().charAt(0);
            String name = scan.next();
            String token = scan.next();
            
            // VVV This might be redundant but I am scared to remove it
            Player p = null;
            
            //Switches based on if char is an H or and A so it checks and adds to correct hashTable
            switch(homeOrAway){
                case 'H':
                    //If hashTable does not contain the player, create a new Player
                    if(!hashHome.containsKey(name))
                        hashHome.put(name, new Player(name));
                    p = hashHome.get(name);
                    break;
                case 'A':
                    //If hashTable does not contain the player, create a new Player
                    if(!hashAway.containsKey(name))
                        hashAway.put(name, new Player(name));
                    p = hashAway.get(name);
                    break;
            }
            if(p!=null){
                //If the player now exists, increment the stat respective to the key
                switch(hashKey.get(token)){
                    case 'H':
                        p.incrementHits();
                        break;
                    case 'O':
                        p.incrementOuts();
                        break;
                    case 'K':
                        p.incrementStrikeouts();
                        break;
                    case 'W':
                        p.incrementWalks();
                        break;
                    case 'S':
                        p.incrementSacrifice();
                        break;
                    case 'E':
                        p.incrementErrors();
                        break;
                    case 'P':
                        p.incrementHbp();
                        break;
                }
            }
        }
        scan.close();
    }
    
    //Parses the key file and populates a hashTable with the key
    public static void getKey(HashMap<String, Character> hashKey) throws FileNotFoundException{
        File keyfile = new File("keyfile.txt");
        Scanner scan = new Scanner(keyfile);
        
        char key ='O';
        String token;
        
        //While there are more keys to parse...
        while(scan.hasNext()){
            token = scan.nextLine();
            //If the line contains the title before the keys, check the title
            //and change the value being added to the key
            if(token.compareTo("")!=0 && token.charAt(0) == '#'){
                if(token.compareTo("## OUTS ##") == 0)
                    key = 'O';
                else if(token.compareTo("## STRIKEOUT ##") == 0)
                    key = 'K';
                else if(token.compareTo("## HITS ##") == 0)
                    key = 'H';
                else if(token.compareTo("## WALK ##") == 0)
                    key = 'W';
                else if(token.compareTo("## SACRIFICE ##") == 0)
                    key = 'S';
                else if(token.compareTo("## HIT BY PITCH ##") == 0)
                    key = 'P';
                else if(token.compareTo("## ERRORS ##") == 0)
                    key = 'E'; 
            }
            //if the data contains the key, put it in the hashTable
            else if(token.compareTo("")!=0){
                hashKey.put(token, key);
            }
        }
        //close scanner to prevent data leaks
        scan.close();
    }
    
    //Function finda and displays leaders for each category
    public static void printLeaders(ArrayList<Player> list){
        
        //Output header for league leaders
        System.out.println("\nLEAGUE LEADERS");
        
        //BATTING AVERAGE-------------------------------------------------------
        
        //Create an array storing the highn values for each place
        double[] hiDoub = {Double.MIN_VALUE,Double.MIN_VALUE,Double.MIN_VALUE}; 
        
        //Declare leaderCount which holds current number of leaders for category
        int leaderCount = 0;
        
        //Output header for Batting Average
        System.out.println("BATTING AVERAGE");
        
        //For 3 places...
        for(int i = 0; i < 3; i++){
            //if i exceeds the list size, break
            if(i > list.size())
                break;
            //for the size of the list, if batting is higher than the high value and not a repeated high value
            //set the high value of place i to this batting average
            for(int j = 0; j<list.size(); j++ ){
                if(list.get(j).calcBA() > hiDoub[i] && list.get(j).calcBA()!= hiDoub[0] && list.get(j).calcBA()!= hiDoub[1]){
                    hiDoub[i] = list.get(j).calcBA();
                }
            }
        }
        
        //for 3 places
        for(int i = 0; i < 3; i++){
            //if the leader count is gte 3 or leader count is greater than size, break
            if(leaderCount >= 3 || leaderCount>list.size()) 
                break;
            //variable holds the number of leaders tied for this place
            int placeLeaderCount = 0;
            //output the high value at place i
            System.out.printf("%.3f\t",hiDoub[i]);
            
            //for list.size, output names of players w BAs equal to the high doub at place i
            for(int j = 0; j < list.size(); j++){
                if(list.get(j).calcBA() == hiDoub[i]){
                    if(placeLeaderCount > 0) 
                        System.out.print(", ");
                    placeLeaderCount++;
                    leaderCount++;
                    System.out.print(list.get(j).getName());
                }
            }
            System.out.println();
        }
        
        System.out.println();
        
        //ON-BASE PERCENTAGE----------------------------------------------------
        
        //Reset hiDoub arrays
        hiDoub[0] = Double.MIN_VALUE;
        hiDoub[1] = Double.MIN_VALUE;
        hiDoub[2] = Double.MIN_VALUE;
        
        //Reset leader count
        leaderCount = 0;
        
        //Output header
        System.out.println("ON-BASE PERCENTAGE");
        
        //For each place...
        for(int i = 0; i < 3; i++){
            //If i > the size of the list, break
            if(i > list.size()) break;
            //for the size of the list, if batting is higher than the high value and not a repeated high value
            //set the high value of place i to this batting average
            for(int j = 0; j<list.size(); j++ ){
                if(list.get(j).calcOB() > hiDoub[i] && list.get(j).calcOB()!= hiDoub[0] && list.get(j).calcOB()!= hiDoub[1]){
                    hiDoub[i] = list.get(j).calcOB();
                }
            }
        }
        //For every place
        for(int i = 0; i < 3; i++){
            //If there are 3 or more leaders or more leaders than elements n list, break
            if(leaderCount >= 3 || leaderCount>list.size()) 
                break;
            //place leader count holds the number of leaders at each place
            int placeLeaderCount = 0;
            //Out put high value
            System.out.printf("%.3f\t",hiDoub[i]);
            
            //for list.size, output names of players w OBs equal to the high doub at place i
            for(int j = 0; j < list.size(); j++){
                if(list.get(j).calcOB() == hiDoub[i]){
                    if(placeLeaderCount > 0) 
                        System.out.print(", ");
                    placeLeaderCount++;
                    leaderCount++;
                    System.out.print(list.get(j).getName());
                }
            }
            System.out.println();
        }
        System.out.println();
        
        
        //HITS------------------------------------------------------------------
        
        //Create high integer array to hold highest ints found in array
        int[] hiInt = {Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE};
        leaderCount = 0;
        
        //Output header
        System.out.println("HITS");
        
        //For 3 places...
        for(int i = 0; i < 3; i++){
            //if i exceeds list size, break
            if(i > list.size()) 
                break;
            //for the size of the list, if hits is higher than the high value and not a repeated high value
            //set the high value of place i to this hits
            for(int j = 0; j<list.size(); j++ ){
                if(list.get(j).getHits() > hiInt[i] && list.get(j).getHits()!= hiInt[0] && list.get(j).getHits()!= hiInt[1]){
                    hiInt[i] = list.get(j).getHits();
                }
            }
        }
        //For 3 places
        for(int i = 0; i < 3; i++){
            //If 3 or more leaders or leader count exceeds list size, break
            if(leaderCount >= 3 || leaderCount>list.size()) 
                break;
            //Holds amount of leaders for place
            int placeLeaderCount = 0;
            //Output high value
            System.out.print(hiInt[i]+"\t");
            //for list.size, output names of players w Hits equal to the high int at place i
            for(int j = 0; j < list.size(); j++){
                if(list.get(j).getHits() == hiInt[i]){
                    if(placeLeaderCount > 0) 
                        System.out.print(", ");
                    placeLeaderCount++;
                    leaderCount++;
                    System.out.print(list.get(j).getName());
                }
            }
            System.out.println();
        }
        System.out.println();
        
        //WALKS-----------------------------------------------------------------
        
        //Reset high integer array
        hiInt[0] = Integer.MIN_VALUE;
        hiInt[1] = Integer.MIN_VALUE;
        hiInt[2] = Integer.MIN_VALUE;
        leaderCount = 0;
        
        //Output header
        System.out.println("WALKS");
        
        //for 3 places...
        for(int i = 0; i < 3; i++){
            //if I exceeds size of list
            if(i > list.size()) break;
            //for the size of the list, if walks is higher than the high value and not a repeated high value
            //set the high value of place i to this walks
            for(int j = 0; j<list.size(); j++ ){
                if(list.get(j).getWalks() > hiInt[i] && list.get(j).getWalks()!= hiInt[0] && list.get(j).getWalks()!= hiInt[1]){
                    hiInt[i] = list.get(j).getWalks();
                }
            }
        }
        
        //for 3 places
        for(int i = 0; i < 3; i++){
            //If 3 or more leaders found or leader count exceeds list size, break
            if(leaderCount >= 3 || leaderCount>list.size()) 
                break;
            //Holds number of leaders at each place
            int placeLeaderCount = 0;
            //Output high value
            System.out.print(hiInt[i]+"\t");
            
            //for list.size, output names of players w walks equal to the high int at place i
            for(int j = 0; j < list.size(); j++){
                if(list.get(j).getWalks() == hiInt[i]){
                    if(placeLeaderCount > 0) 
                        System.out.print(", ");
                    placeLeaderCount++;
                    leaderCount++;
                    System.out.print(list.get(j).getName());
                }
            }
            System.out.println();
        }
        
        System.out.println();
        
        //STRIKEOUTS------------------------------------------------------------
        
        //Reset high int arrays (will hold the low value for strikeouts)
        hiInt[0] = Integer.MAX_VALUE;
        hiInt[1] = Integer.MAX_VALUE;
        hiInt[2] = Integer.MAX_VALUE;
        
        //reset leader count
        leaderCount = 0;
        
        //Output header
        System.out.println("STRIKEOUTS");
        
        //for 3 places
        for(int i = 0; i < 3; i++){
            //If i exceeds  list size, break
            if(i > list.size()) break;
            //for the size of the list, if Strikeouts is lowerhigher than the low value and not a repeated low value
            //set the low value of place i to this strikeouts
            for(int j = 0; j<list.size(); j++ ){
                if(list.get(j).getStrikeouts() < hiInt[i] && list.get(j).getStrikeouts()!= hiInt[0] && list.get(j).getStrikeouts()!= hiInt[1]){
                    hiInt[i] = list.get(j).getStrikeouts();
                }
            }
        }
        //for 3 places
        for(int i = 0; i < 3; i++){
            //if 3 or more leaders found or more leaders than list size, break
            if(leaderCount >= 3 || leaderCount>list.size()) 
                break;
            //holds number of leaders for this place
            int placeLeaderCount = 0;
            //Output high value
            System.out.print(hiInt[i]+"\t");
            //for list.size, output names of players w strikeouts equal to the low int at place i
            for(int j = 0; j < list.size(); j++){
                if(list.get(j).getStrikeouts() == hiInt[i]){
                    if(placeLeaderCount > 0) 
                        System.out.print(", ");
                    placeLeaderCount++;
                    leaderCount++;
                    System.out.print(list.get(j).getName());
                }
            }
            System.out.println();
        }
        System.out.println();
        
        
        //HIT-BY-PITCH----------------------------------------------------------
        
        //Reset high int arrays
        hiInt[0] = Integer.MIN_VALUE;
        hiInt[1] = Integer.MIN_VALUE;
        hiInt[2] = Integer.MIN_VALUE;
        
        //reset leader count
        leaderCount = 0;
        
        //Output header
        System.out.println("HIT BY PITCH");
        
        //For 3 places...
        for(int i = 0; i < 3; i++){
            //if i exceeds list size, break
            if(i > list.size()) break;
            //for the size of the list, if hbp is higher than the high value and not a repeated high value
            //set the high value of place i to this hbp
            for(int j = 0; j<list.size(); j++ ){
                if(list.get(j).getHbp() > hiInt[i] && list.get(j).getHbp()!= hiInt[0] && list.get(j).getHbp()!= hiInt[1]){
                    hiInt[i] = list.get(j).getHbp();
                }
            }
        }
        
        //for 3 places
        for(int i = 0; i < 3; i++){
            //If the number of leaders exceeds 3 or size of list, break
            if(leaderCount >= 3 || leaderCount>list.size()) 
                break;
            //Holds number of leaders at each place
            int placeLeaderCount = 0;
            System.out.print(hiInt[i]+"\t");
            
            //for list.size, output names of players w hbp equal to the high int at place i
            for(int j = 0; j < list.size(); j++){
                if(list.get(j).getHbp() == hiInt[i]){
                    if(placeLeaderCount > 0) 
                        System.out.print(", ");
                    placeLeaderCount++;
                    leaderCount++;
                    System.out.print(list.get(j).getName());
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
