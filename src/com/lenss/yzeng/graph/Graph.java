package com.lenss.yzeng.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.lenss.yzeng.utils.DLNode;
import com.lenss.yzeng.utils.SortedLinkedList;

public class Graph {
	private final int vCount;
	private final int eCount;
	
	private double weight[][];
	private SortedLinkedList<Edge> edgeList;

	public Graph(int vCount, int eCount){
		this.vCount = vCount;
		this.eCount = eCount;
		weight = new double[vCount][vCount];
		for (int i = 0; i < vCount; i++) {
			Arrays.fill(weight[i], -1);
			weight[i][i] = 0;
		}
	}
	
	public Graph(Scanner scanner){
		this(scanner.nextInt(), scanner.nextInt());
		if (this.eCount < 0 || this.vCount < 0) {
			throw new IllegalArgumentException("Number of edges or vertices cannot be negative!");
		}
		
		int v = scanner.nextInt();
		int w = scanner.nextInt();
		double weight = scanner.nextDouble();
		
		this.weight[v][w] = weight;
		this.weight[w][v] = weight;
		this.edgeList = new SortedLinkedList<Edge>(new Edge(v, w, weight));
		
		
		for (int i = 0; i < (this.eCount * 2 - 1); i++) {
			v = scanner.nextInt();
			w = scanner.nextInt();
			weight = scanner.nextDouble();
			
			if(v == w){
				throw new IllegalArgumentException("input file contains edge from one to itself!");
			}else if (this.weight[v][w] == -1) {
				descendingAddEdge(v, w, weight);
			}
		}
	}
	
	public void descendingAddEdge(int v, int w, double weight){
		this.weight[v][w] = weight;
		this.weight[w][v] = weight;
		this.edgeList.descendingInsert(new Edge(v, w, weight));
	}
	
	public static Graph graphInit(String filePath) throws FileNotFoundException{
		Scanner scanner = new Scanner(new File(filePath));
		return (new Graph(scanner));
	}
	
	public void printAdjList(){
		System.out.println(this.vCount + " " + this.eCount);
		for (int i = 0; i < this.vCount; i++) {
			System.out.print(i + "\t");
			for (int j = 0; j < this.vCount; j++) {
				if (weight[i][j] > 0) {
					System.out.print(i + "-" + j + " " + weight[i][j] + "\t");
				}
			}
			System.out.print("\n");
		}
	}
	
	public ArrayList<ArrayList<Integer>> greedyEdgeDivide(int[] deviceCapacity){
		ArrayList<ArrayList<Integer>> allocationMap = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> perDevAllocation;
		//if all topo nodes can be supported on one device
		if (this.vCount <= deviceCapacity[0]) {
			perDevAllocation = new ArrayList<Integer>();
			for (int i = 0; i < this.vCount; i++) {
				perDevAllocation.add(i);
			}
			allocationMap.add(perDevAllocation);
			return allocationMap;
		}
		
		//if the total executor count is less than topo nodes
		
		//deal with the circumstance that all device capacity is 1
		if (deviceCapacity[0] == 1) {
			for (int i = 0; i < vCount; i++) {
				perDevAllocation = new ArrayList<Integer>();
				perDevAllocation.add(i);
				allocationMap.add(perDevAllocation);
			}
		}
		
		//if there is no valid node on the edge list
		DLNode<Edge> node = this.edgeList.getStart();
		if (node.getValue() == null) {
			throw new NullPointerException("the node is empty!");
		}
		
		//main allocation logic
		//allocate the first topo node to the largest capacity device
		perDevAllocation = new ArrayList<Integer>();
		perDevAllocation.add(node.getValue().getV());
		allocationMap.add(perDevAllocation);
		//cumulatively add whenever a device is filled
		int allocatedCount = 0;
		while (node.hasNext()) {
			node = node.getNext();
			//int v = node.getValue().getV(), w = node.getValue().getW();
			ArrayList<Integer> unallocated = new ArrayList<Integer>();
					//allocated = new ArrayList<Integer>(), 
					//allocating = new ArrayList<Integer>();
			//add v and w to unallocated
			unallocated.add(node.getValue().getV());
			unallocated.add(node.getValue().getW());
			//TODO
			//deal with allocated node sets
			for (int i = 0; i < allocatedCount && unallocated.size() != 0; i++) {
				perDevAllocation = allocationMap.get(i);
				for (int j = 0; j < deviceCapacity[i]; j++) {
					int perNode = perDevAllocation.get(j);
					for (int k = 0; k < unallocated.size(); k++) {
						if (unallocated.get(k) == perNode) {
							unallocated.remove(k);
						}
					}
				}
			}
			//which means all nodes in this edge is already allocated
			if (unallocated.isEmpty())
				continue;
			//TODO deal with 1 or 0 nodes in this edge exist in the allocated
			//1 node is already allocated
			else if (unallocated.size() == 1) {
				for (int i = allocatedCount; i < allocationMap.size(); i++) {
					if (allocationMap.get(i).contains(unallocated.get(0))) {
						unallocated.remove(0);
					}
				}
				//1 in allocated, 0 in allocating
				if (unallocated.size() == 1) {
					perDevAllocation = new ArrayList<Integer>();
					perDevAllocation.add(unallocated.get(0));
					allocationMap.add(perDevAllocation);
				}
				//1 in allocated, 1 in allocating, do nothing
			}
			//0 node is allocated
			else {
				ArrayList<Integer> mapIndex = new ArrayList<Integer>();
				for (int i = allocatedCount; i < allocationMap.size(); i++) {
					perDevAllocation = allocationMap.get(i);
					for (int j = 0; j < unallocated.size(); j++) {
						if (perDevAllocation.contains(unallocated.get(j))) {
							unallocated.remove(j);
							mapIndex.add(i);
						}
					}
				}
				if (unallocated.size() == 2) {
					perDevAllocation = new ArrayList<Integer>();
					//TODO what if there is only one device
					perDevAllocation.add(unallocated.get(0));
					perDevAllocation.add(unallocated.get(1));
					unallocated.clear();
				}
				else if (unallocated.size() == 1) {
					int index = mapIndex.get(0);
					perDevAllocation = allocationMap.get(index);
					perDevAllocation.add(unallocated.get(0));
				}
				//two nodes are allocating, try merge
				else {
					
				}
			}
		}
		
		return allocationMap;
	}
	
	public double[][] getWeight() {
		return weight;
	}

	public void setWeight(double[][] weight) {
		this.weight = weight;
	}

	public SortedLinkedList<Edge> getEdgeList() {
		return edgeList;
	}

	public void setEdgeList(SortedLinkedList<Edge> edgeList) {
		this.edgeList = edgeList;
	}

	public int getvCount() {
		return vCount;
	}

	public int geteCount() {
		return eCount;
	}
}
