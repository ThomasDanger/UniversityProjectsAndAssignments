public class DisjointSet {
    
    private int[] sets;
    
    public DisjointSet(int size){
        sets = new int[size];
        
        for(int i = 0; i < sets.length; i++){
            sets[i] = -1;
        }
    }
    
    //Performs arbitrary union between two nodes (node 2 points to node 1)
    public void unionArbitrary(int num1, int num2){
        sets[num2] = num1;
    }
    
    //Performs size-based union between two nodes (node with smaller subtree points to node with larger subtree)
    public void unionSize(int num1, int num2){
        if(sizeOfSubtree(num1)>=sizeOfSubtree(num2))
            sets[num2] = num1;
        else
            sets[num1] = num2;
    }
    
    //Performs size-based union between two nodes (node with smaler rank points to node with larger rank)
    public void unionHeight(int num1, int num2){
        if(heightOfSubtree(num1)>=heightOfSubtree(num2))
            sets[num2] = num1;
        else
            sets[num1] = num2;
    }
    
    //Resursively finds size of subtree ( O(N^2)  )
    public int sizeOfSubtree(int root){
        int count=0;
        for(int i = 0; i< sets.length; i++){
            if(sets[i]==root){
                count++;
                count+= sizeOfSubtree(i);
            }
        }
        return count;
    }
    
    //Recursively finds height of subtree ( O(N^2)  )
    public int heightOfSubtree(int root){
        int maxHeightOfSubtree = -1;
        for(int i = 0; i < sets.length; i++){
            if(sets[i]==root){
                int heightOfSubtree = heightOfSubtree(i);
                if(heightOfSubtree>maxHeightOfSubtree)
                    maxHeightOfSubtree = heightOfSubtree;
            }
        }
        return maxHeightOfSubtree;
    }
    
    //Prints set
    public void print(){
        for(int i = 0; i < sets.length; i++){
            System.out.print(i+"\t");
        }
        System.out.print("\n");
        for(int i = 0; i < sets.length; i++){
            System.out.print(sets[i]+"\t");
        }
        System.out.print("\n");
    }
}
