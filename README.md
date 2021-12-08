# Ex2_OOP

In this assignment we asked to build a graph from zero. including the graph vertixes edges and the most
intersting part, a graph algorithms; such as TSP, Dijkstra etc.

What first we built our vertixes as we asked for, every node contain a geographic location and special unique id
and other fields which helped us in the algorythm implementaion.

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


