package com.lenss.yzeng.graph;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class TopoGraphGen {
	public static void main(String[] args){
		int maxVCount = 30, maxECount = 100; 
		Graph graph = genTopoGraph(maxVCount);
		
		try {
			graph.writeMGraph("randomTopoGraph.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Graph genTopoGraph(int maxVCount){
//		if (maxECount < maxVCount - 1) {
//			throw new IllegalArgumentException("max edge count shall be larger "
//					+ "or equal than max vertex count plus + 1");
//		}
		Random random = new Random();
		//choose to add 2 since we don't want a graph with only 1 node
		int vCount = random.nextInt(maxVCount - 2) + 2;
		//TODO how to leverage maxECount for edge random of specified number
		int eCount = random.nextInt(vCount * vCount - (vCount - 1) - vCount) + vCount - 1;
		int remainingECount = eCount;
		Graph graph = new Graph(vCount, eCount);
		double [][] weight = new double[vCount][vCount];
		for (int i = 0; i < vCount; i++) {
			Arrays.fill(weight[i], -1);
			weight[i][i] = 0;
		}
		
		//shuffled order of all vertices for the first round random generation
		//to ensure graph connectivity
		int order[] = new int[vCount];
		for (int i = 0; i < order.length; i++) {
			order[i] = i;
		}
		shuffleArray(order);
		
		//ensure the connectivity of the graph
		for (int i = 1; i < vCount; i++) {
			//select randomly from vertices before itself in the shuffled vertex array
			int index = random.nextInt(i);
			//range from 0 to 10
			double tmpWeight = 0 + 10 * random.nextDouble();
			weight[order[index]][order[i]] = tmpWeight;
			weight[order[i]][order[index]] = tmpWeight;
		}
		remainingECount = remainingECount - (vCount - 1);
		
		//Yet a new random strategy
		while (remainingECount != 0) {
			int vIndex = random.nextInt(vCount - 1), wIndex = random.nextInt(vCount - 1);
			if (weight[vIndex][wIndex] == -1) {
				double tmpWeight = 0 + 10 * random.nextDouble();
				weight[vIndex][wIndex] = tmpWeight;
				weight[wIndex][vIndex] = tmpWeight;
				remainingECount --;
			}
		}
//		for (int i = 0; i < vCount; i++) {
//			for (int j = 0; j < vCount; j++) {
//				if (j != i) {
//					if (random.nextDouble() > 0.5) {
//						if (weight[i][j] == -1) {
//							eCount ++;
//						}
//						double tmpWeight = 0 + 10 * random.nextDouble();
//						weight[i][j] = tmpWeight;
//						weight[j][i] = tmpWeight;
//					}
//				}
//			}
//		}
		graph.setWeight(weight);
		return graph;
	}
	
	public static void shuffleArray(int[] array){
		Random random = new Random();
	    for (int i = array.length - 1; i > 0; i--)
	    {
	    	int index = random.nextInt(i + 1);
	    	// Simple swap
	    	int temp = array[index];
	    	array[index] = array[i];
	    	array[i] = temp;
	    }
	}
}
