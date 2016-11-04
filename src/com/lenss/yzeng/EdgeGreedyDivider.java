package com.lenss.yzeng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.lenss.yzeng.graph.Edge;
import com.lenss.yzeng.graph.Graph;
import com.lenss.yzeng.utils.DLNode;
import com.lenss.yzeng.utils.SortedLinkedList;

public class EdgeGreedyDivider {
	private Graph graph;
	private int[] deviceCapacity;
	private Map<Integer, ArrayList<Integer>> allocationMap;

	public EdgeGreedyDivider(Graph graph, int[] deviceCapacity) {
		super();
		this.graph = graph;
		this.deviceCapacity = deviceCapacity;
		allocationMap = new HashMap<Integer, ArrayList<Integer>>();
	}
	
	public void greedyEdgeDivide(){
		//3 cases:
				//1. no vertex on the edge exits in current bags
				//2. one vertex on the edge exits
				//3. two vertex exist
		SortedLinkedList<Edge> edgeSorted = this.graph.getEdgeList();
		DLNode<Edge> current = edgeSorted.getStart();
		while (current.hasNext()) {
			
		}
	}
	
	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	public int[] getDeviceCapacity() {
		return deviceCapacity;
	}

	public void setDeviceCapacity(int[] deviceCapacity) {
		this.deviceCapacity = deviceCapacity;
	}
}
