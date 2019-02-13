package vertex;

public class Edge {

	private Vertex destination;
	private int strength = 0;
	
	public Edge(Vertex dest){
		this.destination = dest;
	}
	
	public int getStrength(){
		return this.strength;
	}
	
	public void incrementStrength(){
		this.strength++;
	}
	
	public Vertex getDestination(){
		return this.destination;
	}
	
	public String toString(){
		return "" + strength;
	}
}
