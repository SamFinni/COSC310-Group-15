package vertex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Vertex nodeA = new Vertex("A");
		Vertex nodeB = new Vertex("B");
		Vertex nodeC = new Vertex("C");
		Vertex nodeD = new Vertex("D");
		Vertex nodeE = new Vertex("E");
		Vertex nodeF = new Vertex("F");
		Vertex nodeG = new Vertex("G");

		nodeA.addEdge(nodeB);
		nodeA.addEdge(nodeC);
		nodeB.addEdge(nodeC);
		nodeB.addEdge(nodeD);
		nodeC.addEdge(nodeD);
		nodeD.addEdge(nodeE);
		nodeF.addEdge(nodeC);
		nodeG.addEdge(nodeF);

		/**
		 * System.out.println("A: " + nodeA.listConnections());
		 * System.out.println("B: " + nodeB.listConnections());
		 * System.out.println("C: " + nodeC.listConnections());
		 * System.out.println("D: " + nodeD.listConnections());
		 * System.out.println("E: " + nodeE.listConnections());
		 * System.out.println("F: " + nodeF.listConnections());
		 * System.out.println("G: " + nodeG.listConnections());
		 **/
		
		List<String> reqNodes = new ArrayList<String>();
		Map<String, Vertex> listNodes = new HashMap<String, Vertex>();
		listNodes.put("A", nodeA);
		listNodes.put("B", nodeB);
		listNodes.put("C", nodeC);
		listNodes.put("D", nodeD);
		listNodes.put("E", nodeE);
		listNodes.put("F", nodeF);
		listNodes.put("G", nodeG);
		
		reqNodes.add("C");
		reqNodes.add("G");
		
		System.out.println(reqNodes);
		
		//Query Line
		List<Vertex> resultSet = Vertex.foo(nodeA, reqNodes, 0, new ArrayList<String>());
		for (Vertex v : resultSet) {
			System.out.println(v.getName());
		}
	}

	/***
	 * public Map<Vertex, Vertex> Dijkstra(Vertex source, List<Vertex> Q){
	 * Map<String, Integer> dist = new HashMap<String, Integer>(); Map<Vertex,
	 * Vertex> prev = new HashMap<Vertex, Vertex>();
	 * 
	 * dist.put(source.getName(), 0);
	 * 
	 * while(!Q.isEmpty()){ Vertex u = null; int min = 999;
	 * for(Map.Entry<String, Integer> entry: dist.entrySet()){
	 * if(entry.getValue() < min) u = Q.get(Q.indexOf(entry.getKey())); }
	 * Q.remove(u); Map<String, Edge> currConn = u.listConnections();
	 * for(Map.Entry<String, Edge> entry: currConn.entrySet()){
	 * if(Q.contains(entry.getKey())){ int alt = dist.get(u.getName()) +
	 * entry.getValue().getStrength(); if(alt < dist.get(entry.getKey())){
	 * dist.replace(entry.getKey(), alt);
	 * prev.replace(Q.get(Q.indexOf(entry.getKey())), u); } } }
	 * 
	 * }
	 * 
	 * return prev; }
	 ***/
	/***
	 * public List<Vertex> query(Vertex start, ListString> reqNodes) {
	 * List<Vertex> result = new ArrayList<Vertex>(); result.add(start); Vertex
	 * currNode = start; while (true) { Map<String, Edge> currConn =
	 * currNode.listConnections(); for (String s : reqNodes) { if
	 * (currConn.containsKey(s)) { // If there exists an edge to a required
	 * node, remove the // node requirement and set the next node as current
	 * node // and iterate through them reqNodes.remove(s); currNode =
	 * currConn.get(s).getDestination(); if (reqNodes.isEmpty()) { return
	 * result; } } else if (!reqNodes.isEmpty()) {
	 * 
	 * } } } }
	 ***/

}
