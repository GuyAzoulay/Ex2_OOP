# Ex2_OOP
* Written by Guy Azoulay and Yahalom Chasid.

## Main goal of the Project

In this assignment we asked to build a graph from zero. The main goal of this project is a good and deep understanding of the graph's world, and deal with a code skelaton by yourself, thankes to this assigment we improved our coding skills and started to really understand what is to "think outside of a box".

In this assigment we implement classes such ass Nodes of a graph, vertixes and the most
intersting part, the graph algorithms such as:  
* Center
* TSP
* Dijkstra
* And a GUI interface of graph drawing 


## The GeoLocation class
In this class we created new variable which gives us information about the geo location of 
node.

## The Nodes class
In this class we created a node which held a two main variables in it, The GeoLocation of a node and his id.
The main veriables which we use in this class are:
  - Key (int type)
    - A value which contain the id of the specific node.
  - Geo Location ( class type)
    - A class which represent a node in the space.
  - Tag (int type)
    - A variable which we used to detemine if we visited in a node.
  - Weight (double type)
    - A variable which held the weight of a node use int the edges weight.
  - Info (String type)
    - A variable which we used in to set a fictional color to a node (white, gray and clack).
  - Prev_Node (int type)
    - A variable which contain from which node's id i came from.
    
|Main Functions|Explenation|RunTime|
|---|---|---|
|Nodes constructor | The main constuctor of new node, using his key and Geo Location| O(1)
|Set/get Prev| Setting and Getting the previous id of a node| O(1)
|Get Key| helps us to underatand in which node wre we right now| O(1)
|Set/Get Weight| helps us to know what is the node's weight and change it | O(1)
|Set/Get Info| gives us information about the node's color| O(1)
|Set/Get Tag| gives us information if we visited in this node or not|O(1)
|Srting toString| with is we can actually see the node information| O(1)
|Comparble compareTo| in aim to compare between two nodes's weight we implement this method| O(1)

## The Edgess class
In this class we created a Edge which held a three main variables in it, The edge source, the edge destanation and the weight of a edge.

The main veriables which we use in this class are:
  - Src (int type)
    - A variable which held the node's id that we came from
  - Dest (int type)
    - A variable which held the node's id that we go to.
  - Tag (int type)
    - A variable which we used to detemine if we visited in a edge.
  - Weight (double type)
    - A variable which held the weight of a edge weight.
  - Info (String type)
    - A variable which we used in to set a fictional way to say if i visited in this edge.
  
    
|Main Functions|Explenation|RunTime|
|---|---|---|
|Edges constructor | The main constuctor of new Edge, using his src,dest and weight|O(1)
|Get Source| gives us the node's id that we came from|O(1)
|Get Dest| gives us the node's id that we go to|O(1)
|Get Weight| helps us to know what is the edge's weight and change it | O(1)
|Set/Get Info| gives us information about the edge's, if we visited or not| O(1)
|Set/Get Tag| gives us information if we visited in this edges or not|O(1)
|Srting toString| with is we can actually see the edge information| O(1)


## The Directed Weighted Graph class
The main purpose of this class is to demonstarte how a graph is really look like.
In addtion, in this class we creat a directed weighted graph using the Node class and the Edges class.
We spent a lot of time to choose the best data structures to use in, at the end both of us understand that the best data structure is HashMap.

The main veriables which we use in this class are:
  - Vertix (HashMap type)
    - Here we held all our nodes in HashMap data structure which the Key is the node's id and the Value is The 
      is the node's data.
  - Edges (HashMap of HashMaps type)
    - Here we held all our edges in HashMap of HashMap data structure which the Key is the source id of the edge
      and the value is HashMap which his Key is the destenation and the Value is the edge's data.
  - IntoMe (HashMap of HashSet type)
    - Here we held all the node's ids that have an edge in to me, the key here is the specific node's id, and the       value is HashSet of Integer which is a list of the nodes's ids which connected to me.    
  
    
|Main Functions|Explenation|RunTime|
|---|---|---|
|DirectedG constructor | The main constuctor of new Graph, using the Node and Edges class|O(1)
|Get Node| gives us the node's information with a given id|O(1)
|Get Edge| gives us the Edge's information with a given src and dest(if this id's exsits)|O(1)
|addNode| adding a new node to our data structure(if his id exsist)|O(1)
|Connect| connect between 2 node's using their id's and create new edge in the graph|O(1)
|Itrators function| The itrators functions that can be used to loop through collections| O(1)
|removeNode| in this function we removing a node from our graph, when we do it we need to delet also all of the edges which fo to this node and from this node|O(n)
|removeEdge| here we remove an edge from our graph, we need to delet it from the Edges HashMap and the edges that go into me HashMap|O(1)
|nodeSize| gives us the amount of nodes in our graph|O(1)
|edgeSize| gives us the number of edges in the graph|O(1)

## The Directed Weighted Algorythm Graph class
The main purpose of this class is to implement known graph algorythms on the graph we was created,
we spent a lot of time to try to make this algorythms efficient as much as we can, using known algorythms which help to solve thede problems like dijksta's algorythm and Kosaraju's algorythm.

The main veriables which we use in this class are:
  * Directed Weighted Graph (DirectedWeightedGraph type)
    * here we have all of the graph information we built earlier.
  * shortestpathlist (List type)
    * this variable will help us to save the shortest path nodes 
    
|Main Functions|Explenation|RunTime|
|---|---|---|
|DirectedGAlgo constructor | The main constuctor of the Graph's algorythms, using the DirectedWeightedGraph  class|O(1)|
|init| initialize our graph|O(1)|
|GetGraph|gives us the graph's information|O(1)|
|Copy |creating a deep copy of the graph|O(1)|
|isConnected| this function check if a graph is strongly connected using the DFS method and the Kosaraju algorythm, this function is Boolean, we change our node's tag in the DFS function is there is a node with tag 0 we returned false|O(V +V*(V+E))|
|shortestPathDist| in this function i have used in the Dijksta algorythm to solve this problem, using a queue and Set, first in the Dijkstra algo we update the node's weight to infinity and their color to white except the src node which we update to 0, than we ran on all of the vertixes an edges and update the node's weight to the min weight and return the destanation node's weight|O(V^2 +E)|
|shortestPath| in this function we use the previos function to check which of the nodes are in the shortest path
||using the List we created at first and add the right node every time| O(V^3+E)|
|center| in the center function we find the center of a graph (if it connected) using the shortestPathDist,
||for each node we check the max path to other node and take the maximum one, than we returned the shortest path between all of the nodes maximum path and take his node | O(V^4)
|TSP| in a given list of cities we need to find the shortest path which contain all of them| --
|load| this function loads the json file to our project|O(n)|
|save| saving the file of the graph|O(V*E)

## Analysis for given Graphs

In this assigment we got 3 graph: G1, G2, G3. All of the graph are connected and we check some of the algorythm on them.

## Example for the Graph Analysis

|Algorythm|Graph G1|Graph G2|Graph G3|
|---|---|---|---|
|isConnected|True|True|True|
|center|8|0|40|
|ShortestPathDist for node 0 and node 8|7.43680|3.512|0.904|
|ShortestPath for node 0 and node 3| 0->1->2->3|0->1->2->3|0->2->3|

 
 ## Analysis for a large amount of node
 In this assigment we asked to analyze what will happen in 1000, 10,000, 100,000 vertixes?
 when we ran our code on a 1000 vertixes its took about 10 minutes to find the center of the graph, that because 
 in the center function we check all of the shortest path for every node with all of the other nodes, this action  is very expensive one for a regular computer so it took a long time, in 10,000 vertix the program did it very very slow and we had to stop it after a big amount of time, 100,000 as well.
 
 ## Lesson Learned
 
  ### Things To Improve
    - Create tests during the project
    - Divide more correctly our time between all the assigments
  ### Thing To save
    - Deep learning of the problem space
    - A good Pre designed what we willing to do in that assigment
 
 
 ## Resources
 
 [DFS Algorythm](https://en.wikipedia.org/wiki/Depth-first_search)
 
 [Center in Graph](https://en.wikipedia.org/wiki/Graph_center)
 
 [Kosaraju's algorithm](https://en.wikipedia.org/wiki/Kosaraju%27s_algorithm)
 
 [Dijkstra's algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm)
 
 
 
 
 
