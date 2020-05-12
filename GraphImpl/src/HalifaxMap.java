import java.util.LinkedList;
import java.util.List;

public class HalifaxMap{
	
	/*
	 * Graph class defines whole Map which is list of nodes(Vertices) and Edges
	 * All the nodes and Edges created are added into List
	 * getters and setters to List of edges and nodes are designed
	 */
	private List<Node> nodes=new LinkedList<Node>();
	private List<Edge> edges=new LinkedList<Edge>();
	public List<Edge> getEdges() {
		return edges;
	}
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {

		this.nodes = nodes;
	}
	
	/*
	 * below function creates new vertex and places it into Graph(Map)
	 */
    boolean newIntersection(int x,int y) {
		
    	boolean flag=true;
		Node n=new Node();
		//create the Vertex and assign both x and y coordinates to that
		n.setX(x);
		n.setY(y);
		for(Node node:nodes) {
			
			//Check whether if the vetex is already existed in map or not
			if((node.getX()==n.getX())&&(node.getY()==n.getY())) {
				flag=false;
				break;//assign flag to false which means the vertex is already in map
			}
			
		}
		if(flag) {//if vertex is no there in map then add it to the city map
			nodes.add(n);
		}
        
		return flag;
		
	}
	public boolean defineRoad(int x1, int y1, int x2, int y2) {
		boolean EdgeExist=true;//assigned to check if the route already exists or not
		boolean Edgeinserted=false;
		//Getting the corresponding vertex object for both coordinates
		Node source=getNode(x1, y1);
		Node destination=getNode(x2, y2);
		if(!(source==null||destination==null)&&(source!=destination)) {//if given vertex are not in map then return false
			
			int weight=(int) Math.pow((x2-x1), 2)+(int) Math.pow((y2-y1), 2);//Calculating distance
			for(Edge e:edges) {	
			//Checking the route if its exists or not
				if((e.getSource()==source)&&(e.getDestination()==destination)) {
					EdgeExist=false;
				}
				if((e.getSource()==destination)&&(e.getDestination()==source)) {
					EdgeExist=false;
				}
			}
		//if edge not exists then add it
			if(EdgeExist) {
				
				//creating backward and forward edge to implement bidirectional routes
			    Edge forward=new Edge(source,destination,weight);
			    edges.add(forward);
			    Edge backward=new Edge(destination,source,weight);
			    edges.add(backward);
			    Edgeinserted=true;
			}
		}
		else {
			System.out.println("One of the vertex are not inserted in the map");
		}
		
		return Edgeinserted;
	}
	
	private Node getNode(int x, int y) {
		/*
		 * This private method is designed to get corresponding vertex(Node) for a coordinate
		 */
		Node node=null;
		for(Node n:nodes){
			if((x==n.getX())&&(y==n.getY())) {
				node=n;
				break;
				
			}
		}
		return node;
	}
	public void navigate(int x1,int y1,int x2,int y2) {
		//Getting corresponding Node(Vertex) object for source and destination vertex
		Node source=getNode(x1, y1);
		Node destination=getNode(x2, y2);
		//Shortest path is a class where the shortestpath algorithm is implemented
		ShortestRoute sr=new ShortestRoute(this);
		if(source==null || destination==null) {
			//if one of vertex is not registered with map then prints no path
			System.out.println("No Path");
		}
		else {
			//calls navigate method in ShortestPath
			sr.navigate(source,destination);	
		}
		
	}

	
}
