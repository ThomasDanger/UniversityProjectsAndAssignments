/*

Rudimentary Graph class for the sole purpose of performing a toplogical sort

*/


import java.util.ArrayList;

public class Graph {
    private ArrayList<Vertex> adjList = new ArrayList<>();
    final private String names = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    public Graph(int[][] adjList_){
        
        for(int i = 0; i < adjList_.length; i++){
            Vertex newVert = new Vertex(names.charAt(i));
            adjList.add(newVert);
        }
        
        for(int i = 0; i < adjList_.length; i++){
            for(int j = 0; j < adjList_[i].length; j++){
                if(adjList_[i][j]!=0){
                    adjList.get(i).add(adjList.get(j));
                    adjList.get(j).indegree++;
                }
            }
        }
    }
    
    public ArrayList<Vertex> getAdjList(){
        return adjList;
    }
    
    public int size(){
        return adjList.size();
    }
    
    public Vertex get(int index){
        return adjList.get(index);
    }
    
    public void print(){
        for(Vertex v : adjList){
            v.print();
        }
    }
}
