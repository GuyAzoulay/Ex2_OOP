# Ex2_OOP

## Main goal of the Project

In this assignment we asked to build a graph from zero. The main goal of this project is a good and deep understanding of the graph's world, and deal with a code skelaton by yourself, thankes to this assigment we improved our coding skills and started to really understand what is to "think outside of a box".

In this assigment we implement classes such ass Nodes of a graph, vertixes and the most
intersting part, the graph algorithms such as:  
* Center
*  TSP
* -Dijkstra
*-And a GUI interface of graph drawing 


##The GeoLocation class
In this class we created new variable which gives us information about the geo location of 
node.

## The Nodes class
In this class we created a node which held a two main variables in it, The GeoLocation of a node and his id.
The main veriables which we use in this class are:
  -Key (int type)
    - A value which contain the id of the specific node.
  -Geo Location ( class type)
    - A class which represent a node in the space.
  -Tag (int type)
    - A variable which we used to detemine if we visited in a node.
  -Weight (double type)
    - A variable which held the weight of a node use int the edges weight.
  -Info (String type)
    - A variable which we used in to set a fictional color to a node (white, gray and clack).
  -Prev_Node (int type)
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
  -Src (int type)
    - A variable which held the node's id that we came from
  -Dest (int type)
    -A variable which held the node's id that we go to.
  -Tag (int type)
    - A variable which we used to detemine if we visited in a edge.
  -Weight (double type)
    - A variable which held the weight of a edge weight.
  -Info (String type)
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











Than we created the Edges of the graph which contain a basic information such as source dest and weight, and like
the Node class we had few fields which helped us later.

Next, we created our graph, to think about the data structure which will contain our information was a little bit 
difficult, but we agreed that the best data structure is HashMap, one for the vertixes, and another HashMap special 
HashMap of HashMaps( HashMap <Integer, HashMap<Integer, Edge>>), we use that data structure because in this way,
for every id of any node, we get a a HashMap of all of the destination nodes and their information (src, weight, dest).
And the last one is Hashset of all of the nodes which connected to me, for that we used HashMap of HashSet, with that
we could always know which of the the other verixes is connect to me, that was really helpfull in the graph and the algorythm implementaion.

Now we arrived to the pre-final part of the this assignment, the algorythm part.
In this part we impliment famouse algorythmes such as if the graph is connected, Travel Passenger problem, Dijkstra and more.
To check if a graph is strnogly connected we use the DFS algorythm and with it we changed the color (tags) of the vertixes
until everyone was painted, if one of them was not painted, the graph is not strongly connected.

Next function is the Dijkstra algo, which we helped in to solve the shortest path distance between to vertixes,
here we used the nodes's weight to find the shortest path, at first we changed all of them to infinity except the source node.
Than we pass all of the edges of the same node and startes to run on all of the edges in the graph to check every optional 
path, than we comperd between every node weight to his neighbers's weight, at the end we returned the destanation node with the lowst
weight.

In the ShrtestPath function we used the Dijkstra algo and save all the information of the shortest path in a local variable of list.

In the Center function we ran on each vertix with all og the nodes in the graph and saved the longest shortest path (little bit confusing I know)
of all of the vertixes.
than we returned the index of smallest path between all of the paths.


