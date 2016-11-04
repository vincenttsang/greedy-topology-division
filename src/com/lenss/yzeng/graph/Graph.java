package com.lenss.yzeng.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

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
	
	public void printGMatrix(){
		
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
