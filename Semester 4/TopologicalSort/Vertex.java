/*

Rudimentary Vertex class designed for sole purpose of learning topological sort

*/


import java.util.ArrayList;

public class Vertex {
    private ArrayList<Vertex> adjList;
    public int indegree;
    public int topNum;
    private char data;
    
    public Vertex(char data_){
        indegree = 0;
        adjList = new ArrayList<>();
        topNum = 0;
        data = data_;
    }
    
    public ArrayList<Vertex> getAdjList(){
        return adjList;
    }
    
    
    public Vertex get(int index){
        return adjList.get(index);
    }
    
    public void add(Vertex v){
        adjList.add(v);
    }
    
    public int getOutdegree(){
        return adjList.size();
    }
    
    public Vertex remove(int index){
        adjList.get(index).indegree--;
        return adjList.remove(index);
    }
    
    public String toString(){
        return ""+data;
    }
    
    public void print(){
        System.out.print(data+":\t");
        for(Vertex v : adjList){
            System.out.print(v+", ");
        }
        System.out.print("\n");
    }
    
}
