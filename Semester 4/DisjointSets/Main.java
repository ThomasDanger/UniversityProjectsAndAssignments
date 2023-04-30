
package com.mycompany.main;

public class Main {

    public static void main(String[] args) {
        DisjointSet set = new DisjointSet(17);
        
        System.out.println("ARBITRARY:");
        set.unionArbitrary(1, 2);
        set.unionArbitrary(3, 4);
        set.unionArbitrary(3, 5);
        set.unionArbitrary(1, 7);
        set.unionArbitrary(3, 6);
        set.unionArbitrary(8, 9);
        set.unionArbitrary(1, 8);
        set.unionArbitrary(3, 10);
        set.unionArbitrary(3, 11);
        set.unionArbitrary(3, 12);
        set.unionArbitrary(3, 13);
        set.unionArbitrary(14, 15);
        set.unionArbitrary(16, 0);
        set.unionArbitrary(14, 16);
        set.unionArbitrary(1, 3);
        set.unionArbitrary(1, 14);
        
        set.print();
        
        DisjointSet set1 = new DisjointSet(17);
        System.out.println("SIZE:");
        set1.unionSize(1, 2);
        set1.unionSize(3, 4);
        set1.unionSize(3, 5);
        set1.unionSize(1, 7);
        set1.unionSize(3, 6);
        set1.unionSize(8, 9);
        set1.unionSize(1, 8);
        set1.unionSize(3, 10);
        set1.unionSize(3, 11);
        set1.unionSize(3, 12);
        set1.unionSize(3, 13);
        set1.unionSize(14, 15);
        set1.unionSize(16, 0);
        set1.unionSize(14, 16);
        set1.unionSize(1, 3);
        set1.unionSize(1, 14);
        
        set1.print();
        
        
        DisjointSet set2 = new DisjointSet(17);
        System.out.println("HEIGHT:");
        set2.unionHeight(1, 2);
        set2.unionHeight(3, 4);
        set2.unionHeight(3, 5);
        set2.unionHeight(1, 7);
        set2.unionHeight(3, 6);
        set2.unionHeight(8, 9);
        set2.unionHeight(1, 8);
        set2.unionHeight(3, 10);
        set2.unionHeight(3, 11);
        set2.unionHeight(3, 12);
        set2.unionHeight(3, 13);
        set2.unionHeight(14, 15);
        set2.unionHeight(16, 0);
        set2.unionHeight(14, 16);
        set2.unionHeight(1, 3);
        set2.unionHeight(1, 14);
        
        set2.print();
    }
}
