package com.lenss.yzeng.graph;

import java.io.File;
import com.lenss.yzeng.utils.Utils;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.IllegalFormatCodePointException;
import java.util.Random;
import java.util.Scanner;

public class Graph {
	protected final int vCount;
	protected int eCount;
	
	protected double weight[][];

	public Graph(int vCount, int eCount){
		if (eCount < 0 || vCount < 0) {
			throw new IllegalArgumentException("Number of edges or vertices cannot be negative!");
		}
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
		
		initWeight(scanner);
	}
	
	
	
	public void initWeight(Scanner scanner){
		int v = scanner.nextInt();
		int w = scanner.nextInt();
		double weight = scanner.nextDouble();
		
		this.weight[v][w] = weight;
		this.weight[w][v] = weight;
		
		
		for (int i = 0; i < (this.eCount * 2 - 1); i++) {
			v = scanner.nextInt();
			w = scanner.nextInt();
			weight = scanner.nextDouble();
			
			if(v == w){
				throw new IllegalArgumentException("input file contains edge from one to itself!");
			}else if (this.weight[v][w] == -1) {
				//descendingAddEdge(v, w, weight);
				this.weight[v][w] = weight;
				this.weight[w][v] = weight;
			}
		}
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
	
	public  void writeAdjGraph(FileWriter fileWriter) throws IOException{
		System.out.println("=======================Adjacency Graph=======================");
		fileWriter.write(this.vCount + " " + this.eCount + "\n");
		System.out.println(this.vCount + " " + this.eCount);
		for (int i = 0; i < this.vCount; i++) {
			for (int j = i + 1; j < this.vCount; j++) {
				if (this.weight[i][j] > 0) {
					fileWriter.write(i + " " + j + " " + this.weight[i][j] + "\n");
					System.out.println(i + " " + j + " " + this.weight[i][j]);
//					fileWriter.write(i + " " + j + " " + String.format("%.2f", this.weight[i][j]) + "\n");
//					System.out.println(i + " " + j + " " + String.format("%.2f", this.weight[i][j]));
				}
			}
		}
	}
	
	public void writeMatrixGraph(FileWriter fileWriter) throws IOException{
		System.out.println("====================Matrix Graph=======================");
		fileWriter.write(this.vCount + " " + this.eCount + "\n");
		System.out.println(this.vCount + " " + this.eCount);
		//tailored for matlab input, where unconnected edge is weighted 0
		for (int i = 0; i < this.vCount; i++) {
			for (int j = 0; j < this.vCount; j++) {
				double tmpWeight = weight[i][j] == -1 ? 0 : weight[i][j];
				fileWriter.write(tmpWeight + "\t");
				System.out.print(tmpWeight + "\t");
			}
			fileWriter.write("\n");
			System.out.print("\n");
		}
	}
	
	public double[][] getWeight() {
		return weight;
	}

	public void setWeight(double[][] weight) {
		this.weight = weight;
	}

	public int getvCount() {
		return vCount;
	}

	public int geteCount() {
		return eCount;
	}
	
	
	public static Graph genStrongConnGraph(int minVCount, int maxVCount){
		if (minVCount >= maxVCount) {
			System.out.println("genStrongConnGraph(Device Net Graph): min vertex count less than max vertex count!");
			return null;
		}
		Random random = new Random(System.currentTimeMillis());
		//choose to add 2 since we don't want a graph with only 1 node
		int vCount = random.nextInt(maxVCount - minVCount) + minVCount, 
			eCount = (vCount * vCount - vCount) / 2;
		Graph graph = new Graph(vCount, eCount);
		double [][] weight = new double[vCount][vCount];
		int edgeGen = 0;
		for (int i = 0; i < vCount; i++) {
			for (int j = i + 1; j < vCount; j++) {
				if (i == j) {
					weight[i][j] = 0;
				}
				else {
					//range from 0 to 10
					double tmpWeight = 1.0 + 10 * random.nextDouble();
					//round the precision
					tmpWeight = Utils.round(tmpWeight, 1);
					weight[i][j] = tmpWeight;
					weight[j][i] = tmpWeight;
					edgeGen ++;
				}
			}
		}
		System.out.println(edgeGen);
		graph.setWeight(weight);
		return graph;
	}
	
	public static Graph genTopoGraph(int maxVCount, double eVRatio){
//		if (maxECount < maxVCount - 1) {
//			throw new IllegalArgumentException("max edge count shall be larger "
//					+ "or equal than max vertex count plus + 1");
//		}
		if (eVRatio < 1) {
			throw new IllegalArgumentException("edge to vertex ratio too small!");
		}
		Random random = new Random(System.currentTimeMillis());
		//choose to add 2 since we don't want a graph with only 1 node
		int vCount = random.nextInt(maxVCount - 2) + 2, 
			eCount = random.nextInt((int)(vCount * eVRatio) - (vCount - 1)) + vCount - 1,
			remainingECount = eCount;
		//TODO how to leverage maxECount for edge random of specified number
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
		Utils.shuffleArray(order);
		
		//ensure the connectivity of the graph
		for (int i = 1; i < vCount; i++) {
			//select randomly from vertices before itself in the shuffled vertex array
			int index;
			if (i == 1) 
				index = 0;
			else
				index = random.nextInt(i - 1);
			//range from 0 to 10
			double tmpWeight = 1.0 + 10 * random.nextDouble();
			//round the precision
			tmpWeight = Utils.round(tmpWeight, 1);
			weight[order[index]][order[i]] = tmpWeight;
			weight[order[i]][order[index]] = tmpWeight;
		}
		remainingECount = remainingECount - (vCount - 1);
		
		//Yet a new random strategy
		while (remainingECount != 0) {
			int vIndex = random.nextInt(vCount - 1), wIndex = random.nextInt(vCount - 1);
			if (weight[vIndex][wIndex] == -1) {
				double tmpWeight = 1.0 + 10 * random.nextDouble();
				tmpWeight = Utils.round(tmpWeight, 1);
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
}
