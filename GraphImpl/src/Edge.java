
public class Edge {
	
	/*
	 * The Edge class defines an edge between two vertices
	 * Each Edge contains source,destination and its corresponding weight.
	 * getters and setters are also designed to access all the instances
	 */

	private Node source;
	private Node destination;
	private int weight;
	//Below constructor assigns instance variables whenever an edge is created
	public Edge(Node source, Node destination, int weight) {
		
		this.source=source;
		this.destination=destination;
		this.weight=weight;
		
	}
	public Node getSource() {
		return source;
	}
	public void setSource(Node source) {
		this.source = source;
	}
	public Node getDestination() {
		return destination;
	}
	public void setDestination(Node destination) {
		this.destination = destination;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	
}
