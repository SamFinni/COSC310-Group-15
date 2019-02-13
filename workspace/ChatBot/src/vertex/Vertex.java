package vertex;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vector.Vector;

public class Vertex {

	private String vertex_name;
	private Vector vertex_position;
	private Map<String, Edge> connections = new HashMap<String, Edge>();
	//private List<Vertex> connections;
	
	public Vertex(String name, Vector position){
		this.vertex_name = name;
		this.vertex_position = position;
	}
	public Vertex(String name, double x, double y, double z){
		this.vertex_name = name;
		this.vertex_position = new Vector(x, y, z);
	}
	public Vertex(String name){
		this.vertex_name = name;
		this.vertex_position = new Vector(Math.random() * 10 -1, Math.random() * 10 -1,Math.random() * 10 -1 );
	}
	
	public void addEdge(Vertex other){
		if(connections.containsKey(other.getName())){
			connections.get(other.getName()).incrementStrength();
			other.incrementEdgeValue(this);
		}else{
			connections.put(other.getName(), new Edge(other));
			other.addEdge(this);
		}
	}
	
	public void removeEdge(Vertex other){
		if(connections.containsKey(other.getName())){
			connections.remove(other.getName());
			other.removeEdge(this);
		}
	}
	
	public void setVertexPosition(double x, double y, double z){
		this.vertex_position = new Vector(x, y, z);
	}
	
	public void setVertexPosition(Vector newVector){
		this.vertex_position = newVector;
	}
	
	public void incrementEdgeValue(Vertex other){
		this.connections.get(other.getName()).incrementStrength();
	}
	public Vector getVertexPosition(){
		return this.vertex_position;
	}
	
	public Map<String, Edge> listConnections(){
		return this.connections;
	}
	
	public String getName(){
		return this.vertex_name;
	}
	
	public void moveTowards(Vertex other){
		this.vertex_position.augmentBy(other.getVertexPosition());
		if(this.vertex_position.checkProximity(other.getVertexPosition())){
			addEdge(other);
		}
	}
	
	public static List<Vertex> foo(Vertex currNode, List<String> reqNodes, int depth, List<String> visited) {
		// Stop at the 3rd recursion OR if there are no longer any more reqNodes
		if (depth <= 3 && reqNodes.size() > 0) {
			Map<String, Edge> currConn = currNode.listConnections();
			for (String v : visited){
				if(currConn.containsKey(v)){					
					currConn.remove(v);
				}
			}

			List<Vertex> result = new ArrayList<Vertex>();
			result.add(currNode);
			visited.add(currNode.getName());

			/***
			 * for (String s : reqNodes) { if (currConn.containsKey(s) &&
			 * reqNodes.size() == 1) {
			 * result.add(currConn.get(s).getDestination()); visited.add(s);
			 * return result; } else if (currConn.containsKey(s)) { List<String>
			 * tempReq = reqNodes; tempReq.remove(s); List<Vertex> subList =
			 * foo(currConn.get(s).getDestination(), tempReq, 0, visited); if
			 * (subList == null) { continue; } else if (subList.size() == 1) {
			 * continue; } else { result.addAll(subList); return result; } } }
			 ***/
			// Initial Immediate Check
			Vertex firstCheck = checkImmediate(currNode, reqNodes, visited);
			if (firstCheck != null) {
				List<String> tempReq = reqNodes;
				tempReq.remove(firstCheck.getName());
				List<Vertex> tempRst = foo(firstCheck, tempReq, depth + 1, visited);
				if (tempRst != null) {
					//System.out.println("Termination Point 2::");
					result.addAll(tempRst);
					return result;
				}
			}

			// If none of the immediate connections have any req, check
			// connected nodes
			// For each edge, check if that node satisfies any of the
			// immediate reqNodes, if so, perform DFS
			for (Map.Entry<String, Edge> entry : currConn.entrySet()) {
				Vertex immVertex = checkImmediate(entry.getValue().getDestination(), reqNodes, visited);
				if (immVertex != null) {
					List<Vertex> tempResult = foo(immVertex, reqNodes, depth + 1, visited);
					if (tempResult == null) {
						continue;
					} else {
						//System.out.println("Termination Point 1::");
						result.add(entry.getValue().getDestination());
						result.addAll(tempResult);
						return result;
					}
				} else {
					continue;
				}
			}

			// If None of the immediate connections satisfy the previous
			// constrained
			for (Map.Entry<String, Edge> entry : currConn.entrySet()) {
				Map<String, Edge> subConn = entry.getValue().getDestination().listConnections();
				for (Map.Entry<String, Edge> subEntry : subConn.entrySet()) {
					Vertex immVertex = checkImmediate(subEntry.getValue().getDestination(), reqNodes, visited);
					if (immVertex != null) {
						List<Vertex> tempResult = foo(immVertex, reqNodes, depth + 1, visited);
						if (tempResult == null) {
							continue;
						} else if (tempResult.size() == 1) {
							continue;
						} else {
							result.add(subEntry.getValue().getDestination());
							result.addAll(tempResult);
							return result;
						}
					} else {
						continue;
					}
				}
			}

			return result;
		} else if (reqNodes.size() == 0) {
			List<Vertex> result = new ArrayList<Vertex>();
			result.add(currNode);
			return result;
		} else
			return null;

	}

	public static Vertex checkImmediate(Vertex currNode, List<String> reqNodes, List<String> visited) {
		Map<String, Edge> currConn = currNode.listConnections();
		for (String v : visited)
			currConn.remove(v);

		for (String s : reqNodes) {
			if (currConn.containsKey(s)) {
				//System.out.println("Check Immediate Returned: " + s);
				return currConn.get(s).getDestination();
			}
		}
		// System.out.println("Check Immediate Returned Null");
		return null;
	}

}
