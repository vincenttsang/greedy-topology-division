package com.lenss.yzeng.graph;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class TopoGraphGen {
	public static void main(String[] args){
		Random random = new Random();
		//choose to add 2 since we don't want a graph with only 1 node
		int vCount = random.nextInt(30) + 2;
		int eCount = vCount - 1;
		double [][] weight = new double[vCount][vCount];
		for (int i = 0; i < vCount; i++) {
			Arrays.fill(weight[i], -1);
			weight[i][i] = 0;
		}
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
		
		for (int i = 0; i < vCount; i++) {
			for (int j = 0; j < vCount; j++) {
				if (j != i) {
					if (random.nextDouble() > 0.5) {
						if (weight[i][j] == -1) {
							eCount ++;
						}
						double tmpWeight = 0 + 10 * random.nextDouble();
						weight[i][j] = tmpWeight;
						weight[j][i] = tmpWeight;
					}
				}
			}
		}
		
		try {
			writeMGraph(weight, vCount, eCount);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void writeMGraph(double[][] weight, int vCount, int eCount) throws IOException{
		FileWriter fileWriter = new FileWriter("randomTopoGraph.txt");
		fileWriter.write(vCount + " " + eCount + "\n");
		System.out.println(vCount + " " + eCount);
		for (int i = 0; i < vCount; i++) {
			for (int j = i + 1; j < vCount; j++) {
				if (weight[i][j] > 0) {
					fileWriter.write(i + " " + j + " " + weight[i][j] + "\n");
					System.out.println(i + " " + j + " " + weight[i][j]);
				}
			}
		}
		fileWriter.close();
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
