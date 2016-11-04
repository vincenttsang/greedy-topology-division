package com.lenss.yzeng.graph;

import java.io.FileNotFoundException;

public class GraphTest {
	public static void main(String[] args){
		try {
			Graph graph = Graph.graphInit("tinyEWG.txt");
			graph.printAdjList();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
