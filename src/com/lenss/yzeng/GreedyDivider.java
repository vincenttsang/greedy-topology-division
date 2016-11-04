package com.lenss.yzeng;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import com.lenss.yzeng.graph.Graph;

public class GreedyDivider {
	public static void main(String[] args){
		//3 cases:
		//1. no vertex on the edge exits in current bags
		//2. one vertex on the edge exits
		//3. two vertex exist
		try {
			Graph graph = Graph.graphInit("test-no-merge.txt");
			int[] deviceCapacity = {3, 3, 3};
			ArrayList<ArrayList<Integer>> allocationMap =  graph.greedyEdgeDivide(deviceCapacity);
			printAllocationMap(allocationMap);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void printAllocationMap(ArrayList<ArrayList<Integer>> allocationMap){
		for (int i = 0; i < allocationMap.size(); i++) {
			System.out.print("Device " + i + ": ");
			for (Integer node : allocationMap.get(i)) {
				System.out.print(node + " ");
			}
			System.out.print("\n");
		}
	}
}
