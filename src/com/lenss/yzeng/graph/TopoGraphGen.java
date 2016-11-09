package com.lenss.yzeng.graph;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import javax.rmi.CORBA.Util;

import com.lenss.yzeng.utils.Utils;

public class TopoGraphGen {
	public static void main(String[] args){
		//variables for application topo graph
		int maxVCount = 30; 
		double eVRatio = 1.5;
		//for device graph
		int maxDevCount = 15;
		
		
		for (int i = 0; i < 20; i++) {
			Graph topoGraph = Graph.genTopoGraph(maxVCount, eVRatio);
			Graph devGraph = Graph.genStrongConnGraph(maxDevCount);
			//1.25 sets the current total executor number is 1.25 times task number
			int actualDevCount = devGraph.getvCount(), totalExe = (int)(topoGraph.getvCount() * 2);
			int[] devExeArray = Utils.randNumFixedSum(actualDevCount, totalExe);
			
			try {
				//MATLEB test cases
				FileWriter fileWriterML = new FileWriter(".\\testcases\\ml-testcase" + i);
				//Greedy algorithm test cases
				FileWriter fileWriterG = new FileWriter(".\\testcases\\g-testcase" + i);
				
				topoGraph.writeMatrixGraph(fileWriterML);
				devGraph.writeMatrixGraph(fileWriterML);
				Utils.writeDevExecutors(devExeArray, fileWriterML);
				
				topoGraph.writeAdjGraph(fileWriterG);
				devGraph.writeAdjGraph(fileWriterG);
				Utils.writeDevExecutors(devExeArray, fileWriterG);
				
				fileWriterG.close();
				fileWriterML.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		Graph topoGraph = Graph.genTopoGraph(maxVCount, eVRatio);
//		Graph devGraph = Graph.genStrongConnGraph(maxDevCount);
//		//1.25 sets the current total executor number is 1.25 times task number
//		int actualDevCount = devGraph.getvCount(), totalExe = (int)(topoGraph.getvCount() * 1.25);
//		int[] devExeArray = Utils.randNumFixedSum(actualDevCount, totalExe);
//		
//		try {
//			FileWriter fileWriter = new FileWriter("randomAdjTopo.txt");
//			topoGraph.writeAdjGraph(fileWriter);
//			fileWriter.close();
//			
//			fileWriter = new FileWriter("randomMatrixTopo.txt");
//			topoGraph.writeMatrixGraph(fileWriter);
//			fileWriter.close();
//			
//			fileWriter = new FileWriter("randomDevAdjGraph.txt");
//			devGraph.writeAdjGraph(fileWriter);
//			fileWriter.close();
//			
//			fileWriter = new FileWriter("randomDevMatrixGraph.txt");
//			devGraph.writeMatrixGraph(fileWriter);
//			fileWriter.close();
//			
//			fileWriter = new FileWriter("randomDevExecutors.txt");
//			writeDevExecutors(devExeArray, fileWriter);
//			fileWriter.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	
}
