package com.lenss.yzeng.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.lenss.yzeng.utils.DLNode;
import com.lenss.yzeng.utils.SortedLinkedList;

public class Graph {
	protected final int vCount;
	protected final int eCount;
	
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
	
	public  void writeMGraph(String filePath) throws IOException{
		FileWriter fileWriter = new FileWriter(filePath);
		fileWriter.write(this.vCount + " " + this.eCount + "\n");
		System.out.println(this.vCount + " " + this.eCount);
		for (int i = 0; i < this.vCount; i++) {
			for (int j = i + 1; j < this.vCount; j++) {
				if (this.weight[i][j] > 0) {
					fileWriter.write(i + " " + j + " " + this.weight[i][j] + "\n");
					System.out.println(i + " " + j + " " + this.weight[i][j]);
				}
			}
		}
		fileWriter.close();
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
}
