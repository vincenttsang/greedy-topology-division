package com.lenss.yzeng;

import java.util.ArrayList;
import java.util.Map;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Edge;
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
		
	}
}
