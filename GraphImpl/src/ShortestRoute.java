import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class ShortestRoute {
	/*
	 * Created visited and unvisited HashMap to keep track of visisted and unvisisted vertex
	 * key for map is vertex and value is its corresponding weight from source
	 */
	private List<Edge> edges;
	private Map<Node,Integer> visited;
	private Map<Node,Integer> unvisited;
	public ShortestRoute(HalifaxMap g) {
		
		this.edges=g.getEdges();//gets all the edges from map object
		
	}

	public void navigate(Node source, Node destination) {
		
		
		visited=new HashMap<Node,Integer>();
		unvisited=new HashMap<Node,Integer>();
		List<Edge> neighbourstoIndex=null;//stores neighbours of indexed vertex
		//pathvisisted is to keep track of all the path from source vertex
		HashMap<Node,List<Node>> pathvisited=new HashMap<Node,List<Node>>();
		
		unvisited.put(source, 0);
		int distance=0;
		Node indexed=source;//index is created which will be iterating through the map
		Entry<Node, Integer> shortest=null;
		//enters while loop if unvisistd is empty or neighbours of index are present
		while(unvisited.size()>0 || !neighbourstoIndex.isEmpty() ) {
			List<Node> pathnodes=null;
		    visited.put(source, 0);
		    unvisited.remove(source);
		    //getNeighbour is method which returns all the unvisited neighbors
			List<Edge> neighbourslist=getNeighbour(indexed);
			
			for(Edge neighbour:neighbourslist) { //iterates through neighbors and push them into unvisited
				
				if(unvisited.containsKey(neighbour.getDestination())){
					//if the unvisited contains all the shortest path
					if (unvisited.get(neighbour.getDestination())>(neighbour.getWeight()+distance)) {
						
						//replaces the unvisisted key with distance
						unvisited.replace(neighbour.getDestination(), neighbour.getWeight()+distance);
						
						Node temp=indexed;
						List<Node> tempnodes=null;//this tracks the path of vertex traversed
						pathnodes=new LinkedList<Node>();//creates all the list of nodes traveled
						while(pathvisited.containsKey(temp)) {
						
							//list of nodes traverse and its corresponding shortes path
							tempnodes=pathvisited.get(temp);
							temp=tempnodes.get(tempnodes.size()-1);
							pathnodes.add(temp);
						}
						if(pathnodes.isEmpty()) {
							pathnodes.add(indexed);
							//enters node to visited path
							pathvisited.put(neighbour.getDestination(), pathnodes);
							
						}
						else {
							pathnodes.add(indexed);
							//replaced the existing key
							pathvisited.replace(neighbour.getDestination(), pathnodes);
						}
						
						
					}
				}
				else {
					/*
					 * same implementation like above when the node is not in unvisited
					 */
					unvisited.put(neighbour.getDestination(), (neighbour.getWeight()+distance));
					Node temp=indexed;
					List<Node> tempnodes=null;
					pathnodes=new LinkedList<Node>();
					while(pathvisited.containsKey(temp)) {
					
		
						tempnodes=pathvisited.get(temp);
						temp=tempnodes.get(tempnodes.size()-1);
						pathnodes.add(temp);
						
						
					}
					if(pathnodes.isEmpty()) {
						pathnodes.add(indexed);
						pathvisited.put(neighbour.getDestination(), pathnodes);
					}
					else {
						pathnodes.add(indexed);
						pathvisited.replace(neighbour.getDestination(), pathnodes);
					}
				}
				
				
			}
			
			shortest=getMinimumWeight(unvisited);
		    //Grabbing minimum weight node 
			for(Edge e:edges) {
			
				if(!(e.getSource()==indexed)&&(e.getDestination()==shortest.getKey())) {
					distance=0;
					//makes the distance to zero when the indexed is not the neighbours of it
				}
				
			}
				
			//Remove the vertex from unvisisted
			unvisited.remove(shortest.getKey());
			indexed=shortest.getKey(); //making the shortest weight as indexed
			distance=distance+shortest.getValue();//updating the distance traveled
			visited.put(indexed, distance);//make the indexed node into visited
			neighbourstoIndex=getNeighbour(indexed);//check if the indexed have any neighbours.
		}
		if(visited.containsKey(destination)) {
			//if the destination vertex is in unvisited
			for(Entry <Node,Integer> e:visited.entrySet()) {
				
				
				if(destination==e.getKey()) {
					for(Entry<Node,List<Node>> path:pathvisited.entrySet()) {
						//print the shortest path
						if(path.getKey()==destination) {
							System.out.println("Here is navigation for shortest path  "+source+"-->"+path.getValue()+"-->"+destination);
						}	
					}
				}
				
			}
		}
		else {
			//prints no path if the destination is no in visited i.e the destination has no route
			System.out.println("No path");
		}
	
		
	}
	private Entry<Node,Integer> getMinimumWeight(Map<Node, Integer> unsortMap){
		/*
		 * This methods returns the minimum edge of the given map
		 */
		Entry<Node, Integer> min = null;
		for (Entry<Node, Integer> entry : unsortMap.entrySet()) {
		    if (min == null || min.getValue() > entry.getValue()) {
		        min = entry;
		    }
		}
		return min;
		
	}
 
	private List<Edge> getNeighbour(Node source) {
		/*
		 * returns all the unvisited neighbors
		 */
		
		List<Edge> neighbours=new LinkedList<Edge>();

		for(Edge e:edges) {
			
			if(e.getSource()==source && !visited.containsKey(e.getDestination())) {
				neighbours.add(e);
			}
			
		}
		return neighbours;
	}	
	
}
