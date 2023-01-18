//Thomas Conner

//Player Class holds a players name and stats. implements Comparable interface

package com.mycompany.main;

public class Player implements Comparable<Player>{
    
    //Player name and stats as member variables
    private String name;
    private int hits;
    private int outs;
    private int strikeouts;
    private int walks;
    private int hbp;
    private int sacrifice;
    private int errors;
    
    public Player(){
        name = "";
        hits = 0;
        outs = 0;
        strikeouts = 0;
        walks = 0;
        hbp = 0;
        sacrifice = 0;
        errors = 0;
    }
    
    public Player(String name_){
        name = name_;
        hits = 0;
        outs = 0;
        strikeouts = 0;
        walks = 0;
        hbp = 0;
        sacrifice = 0;
        errors = 0;
    }
    
    //Player constructor
    public Player(String name_, int hits_, int outs_, int strikeouts_, int walks_, int hbp_, int sacrifice_, int errors_){
        name = name_;
        hits = hits_;
        outs = outs_;
        strikeouts = strikeouts_;
        walks = walks_;
        hbp = hbp_;
        sacrifice = sacrifice_;
        errors = errors_;
    }
    
    //Accessor Functions
    public String getName(){
        return name;
    }
    public int getHits(){
        return hits;
    }
    public int getOuts(){
        return outs;
    }
    public int getStrikeouts(){
        return strikeouts;
    }
    public int getWalks(){
        return walks;
    }
    public int getHbp(){
        return hbp;
    }
    public int getSacrifice(){
        return sacrifice;
    }
    public int getAtBats(){
        return hits+outs+strikeouts+errors;
    }
    public int getPlateAppearances(){
        return hits+outs+strikeouts+walks+hbp+sacrifice;
    }
    public int getErrors(){
        return errors;
    }
    
    //Mutator Functions
    public void setName(String name_){
        name = name_;
    }
    public void setHits(int hits_){
        hits = hits_;
    }
    public void setOuts(int outs_){
        outs = outs_;
    }
    public void setStrikeouts(int strikeouts_){
        strikeouts = strikeouts_;
    }
    public void setWalks(int walks_){
        walks = walks_;
    }
    public void setHbp(int hbp_){
        hbp = hbp_;
    }
    public void setSacrifice(int sacrifice_){
        sacrifice = sacrifice_;
    }
    public void setErrors(int errors_){
        errors = errors_;
    }
    
    //Increment
    public void incrementHits(){
        hits++;
    }
    public void incrementOuts(){
        outs++;
    }
    public void incrementStrikeouts(){
       strikeouts++;
    }
    public void incrementWalks(){
        walks++;
    }
    public void incrementHbp(){
        hbp++;
    }
    public void incrementSacrifice(){
        sacrifice++;
    }
    public void incrementErrors(){
        errors++;
    }
    
    //Calculator Functions
    public double calcBA(){
        if(this.getAtBats()>0)
            return ((double)hits)/((double)(this.getAtBats()));
        else return 0;
    }
    public double calcOB(){
        if(this.getPlateAppearances()>0)
            return (double)(hits+walks+hbp)/(double)(this.getPlateAppearances());
        else
            return 0;
    }
    
    //Overriden toString function and overriden compareTo function
    @Override
    public String toString(){
        return String.format("%s\t%d\t%d\t%d\t%d\t%d\t%d\t%.3f\t%.3f", name, this.getAtBats(), hits, walks, strikeouts, hbp, sacrifice, (double)Math.round(1000*this.calcBA())/1000, (double)Math.round(1000*this.calcOB())/1000);
    }
    @Override
    public int compareTo(Player p){
        return name.compareTo(p.getName());
    }
}
