package com.lenss.yzeng.graph;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import com.lenss.yzeng.utils.Utils;

public class TopoGraphGen {
	public static void main(String[] args){
		//variables for application topo graph
		int maxVCount = 30; 
		double eVRatio = 1.5;
		//for device graph
		int maxDevCount = 15;
		
		Graph topoGraph = Graph.genTopoGraph(maxVCount, eVRatio);
		Graph devGraph = Graph.genStrongConnGraph(maxDevCount);
		
		int actualDevCount = devGraph.getvCount(), totalExe = (int)(topoGraph.getvCount() * 1.25);
		int[] devExeArray = Utils.randNumFixedSum(actualDevCount, totalExe);
		
		try {
			FileWriter fileWriter = new FileWriter("randomAdjTopo.txt");
			topoGraph.writeAdjGraph(fileWriter);
			fileWriter.close();
			
			fileWriter = new FileWriter("randomMatrixTopo.txt");
			topoGraph.writeMatrixGraph(fileWriter);
			fileWriter.close();
			
			fileWriter = new FileWriter("randomDevAdjGraph.txt");
			devGraph.writeAdjGraph(fileWriter);
			fileWriter.close();
			
			fileWriter = new FileWriter("randomDevMatrixGraph.txt");
			devGraph.writeMatrixGraph(fileWriter);
			fileWriter.close();
			
			fileWriter = new FileWriter("randomDevExecutors.txt");
			writeDevExecutors(devExeArray, fileWriter);
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void writeDevExecutors(int[] devExeArray, FileWriter fileWriter) throws IOException{
		System.out.println("===============Writing Dev Executors=============");
		System.out.println(devExeArray.length);
		fileWriter.write(devExeArray.length + "\n");
		for (int dev : devExeArray) {
			System.out.print(dev + " ");
			fileWriter.write(dev + " ");
		}
	}
}
