// Illustration of Dijkstra's algorithm in Java.
//
// We explain it for one particular sample graph.  In practice, it
// would be better to make it to work for a weighted-graph interface.
// We should discuss this in the lecture.

import java.util.*;
public class Dijkstra {
    
  // Better to work with a more general numerical type.
  // But here we use int, and MAX_VALUE to represent infinity,
  // written oo here.

  public static final int oo=Integer.MAX_VALUE;


  // Our sample graph follows. But we definitely don't want to include
  // a particular graph in the implementation of the algorthm.
  //
  // Our vertices (or nodes) are airports.
  // The edges are labeled by distances.

  public static final int NUM_VERTICES=8;
    
  //now define the cities
  public static final int HNL = 0;
  public static final int SFO = 1;
  public static final int LAX = 2;
  public static final int ORG = 3;
  public static final int DFW = 4;
  public static final int LGA = 5;
  public static final int PVD = 6;
  public static final int MIA = 7;
  public static final int nonexistent = 8;
        
  //start and end vertices
  public static final int FIRST_VERTEX = HNL;
  public static final int LAST_VERTEX = MIA;
    
  // 
  //list of names of cities, for output
  private String[] name = {"HNL","SFO","LAX","ORD","DFW","LGA","PVD","MIA"};
    
  //now the initial distance matrix ("weight")
  private int weight[][] = 
  {
    /* HNL    SFO    LAX     ORD    DFW    LGA     PVD     MIA */
      {0,     oo,    2555,   oo,    oo,    oo,     oo,     oo },  //HNL
      {oo,    0,     337,    1843,  oo,    oo,     oo,     oo },  //SFO
      {2555,  337,   0,      1743,  1233,  oo,     oo,     oo },  //LAX
      {oo,    1843,  1742,   0,     802,   oo,     849,    oo },  //ORD
      {oo,    oo,    1233,   802,   0,     1387,   oo,     1120}, //DFW
      {oo,    oo,    oo,     oo,    387,   0,      142,    1099}, //LGA
      {oo,    oo,    oo,     849,   oo,    142,    0,      1205}, //PVD
      {oo,    oo,    oo,     oo,    1120,  1099,   1205,   0   }, //MIA
    };
    
  /*** Dijkstra's Algorithm starts here ***/

  // The estimates are upper bounds to begin with (=infinity).  The
  // algorithm decreases the estimates until they are all tight.

  int[] distance = new int[NUM_VERTICES]; // Called D in the Foundations handout.
  boolean[] tight = new boolean[NUM_VERTICES];

  // We use "predecessor" to keep track of the predecessor of a node
  // so that we can recover the path.
  int[] predecessor = new int[NUM_VERTICES];
    
  private int minimalPending(){
    int j,u;

    // First find a node whose estimate is non-tight.
    for(j = FIRST_VERTEX; j<LAST_VERTEX; j++){
      if(!tight[j])
        break;
    }
        
    assert(j <= LAST_VERTEX);
        
    // Then find a node whoses estimate is non-tight and minimal.

    u = j; 
    // u is now the first vertex with non-tight estimate, but maybe
    // not the minimal one. 
    for(j++; j <= LAST_VERTEX; j++)
      if(!tight[j] && distance[j]<distance[u])
        u = j;
        
    return u;
  }
    
  private boolean successor(int u, int z){
    return ((weight[u][z] != oo) && u != z);
  }
    
  public void dijkstra(int s){
    //now initialise these arrays
    int z,u;
    int i;
        
    distance[s] = 0;
    for(z = FIRST_VERTEX; z <= LAST_VERTEX; z++){
      if(z != s) 
        distance[z] = oo;

      tight[z] = false;
      predecessor[z] = nonexistent;
    }
    
    // This is the main loop of Dijkstra's algorithm.
    for(i = 0; i<NUM_VERTICES; i++){
      u = minimalPending();
      tight[u] = true;
            
      if(distance[u] == oo)
        continue;
            
      for(z = FIRST_VERTEX; z <= LAST_VERTEX; z++) {
        int d = distance[u]+weight[u][z]; // Maybe a shortcut.
        if(successor(u,z) && !tight[z] && d<distance[z]){
          distance[z] = d; // Indeed a shortcut.
          predecessor[z] = u;
        }
      }
    }      
  }
    
  public void printShortestPath(int origin, int destination){
        
    assert(origin != nonexistent && destination != nonexistent);
    dijkstra(origin);
    
    System.out.println("The shortest path from "
                       +name[origin]
                       +" to "+name[destination]+" is:\n");
        
    Stack<Integer> st = new Stack<Integer>();

    for(int v = destination; v != origin; v = predecessor[v])
      if(v == nonexistent){
        System.out.println("non-existent (because graph is not connected).");
        return;
      }else{
        st.push(v);
      }
    
    st.push(origin);
        
    while(!st.empty())
      System.out.print(name[st.pop()]+" -> ");
    
    System.out.println("[finished]");
  }
    
  public static void main(String[] unused){
    new Dijkstra().printShortestPath(SFO, MIA);
  }
  
}