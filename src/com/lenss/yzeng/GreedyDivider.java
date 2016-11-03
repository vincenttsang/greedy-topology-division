package com.lenss.yzeng;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.omg.PortableServer.ServantActivator;

import edu.princeton.cs.algs4.Bag;
import com.lenss.yzeng.Edge;
import com.lenss.yzeng.Edge.EDGE_CONN;

import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.QuickX;

public class GreedyDivider {
	
	
	public GreedyDivider() {
		// TODO Auto-generated constructor stub
	}
	
	public EdgeWeightedGraph[] greedyDivide(int [] sortedExecutor, EdgeWeightedGraph graph){
		//Quick.sort(a);
		if (sortedExecutor.length < graph.E()) {
			return null;
		}
		ArrayList<Edge> sortedEdges = graph.getSortedEdges();
		Bag<Edge>[] subTopo = (Bag<Edge>[]) new Bag[sortedExecutor.length];
		for (int i = 0; i < sortedEdges.size(); i++) {
			
		}
	}
	
	public static void addEdgeToTopo(Bag<Edge>[] subTopo, Edge edge, int maxExecutor){
		//3 cases:
		//1. no vertex on the edge exits in current bags
		//2. one vertex on the edge exits
		//3. two vertex exist
		for (int i = 0; subTopo[i].size() != 0; i++) {
			Iterator<Edge> edgeBagItr = subTopo[i].iterator();
			while (!edgeBagItr.hasNext()) {
				Edge edgeFromBag = edgeBagItr.next();
				if (edge.edgeConnCheck(edgeFromBag) == EDGE_CONN.SAME) {
					
				}
				else if (edge.edgeConnCheck(edgeFromBag) == EDGE_CONN.CONNECTED) {
					
				}
				else if (edge.edgeConnCheck(edgeFromBag) == EDGE_CONN.UNCONNECTED) {
					
				}
				//error occurred
				else {
					
				}
			}
		}
	}
}
