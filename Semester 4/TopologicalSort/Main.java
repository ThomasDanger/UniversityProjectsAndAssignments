
package com.mycompany.main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        
        int[][] graph = {
            {0,1,0,0,0,0,0},
            {0,0,1,1,1,0,0},
            {0,0,0,0,1,0,0},
            {0,0,0,0,1,0,0},
            {0,0,0,0,0,1,0},
            {0,0,0,0,0,0,0},
            {0,0,0,1,0,0,0}
                };
        
        
        Graph g = new Graph(graph);
        
        g.print();
        System.out.println();
        
        try{
            ArrayList<Vertex> list = topSort(g);

            for(Vertex v : list)
                System.out.print(v+", ");
        }
        catch(Exception e){
            System.out.println("Error");
        }
        
        //g.print();
        
    }
    
    /*
        A   B   C   D   E   F   G
    A   0   1   0   0   0   0   0
    B   0   0   1   1   1   0   0
    C   0   0   0   0   1   0   0
    D   0   0   0   0   1   0   0
    E   0   0   0   0   0   1   0
    F   0   0   0   0   0   0   0
    G   0   0   0   1   0   0   0
    
    A, G, B, C, D, E, F
    
    */
    public static ArrayList<Vertex> topSort(Graph g) throws Exception{
        Queue<Vertex> q = new LinkedList<>();
        ArrayList<Vertex> list = new ArrayList<>();
        
        for(int i = 0; i < g.size(); i++){
            if(g.get(i).indegree == 0)
                q.add(g.get(i));
        }
        
        while(!q.isEmpty()){
            Vertex v = q.remove();
            list.add(v);
            for(int i = 0; i < v.getOutdegree(); i++){
                Vertex w = v.get(i);
                if(--w.indegree == 0)
                    q.add(w);
            }
        }
        return list;
    }
}
