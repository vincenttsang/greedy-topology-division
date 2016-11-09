package com.lenss.yzeng.graph;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import com.lenss.yzeng.utils.Utils;

public class NeuralTopoGraph extends TopoGraph{
	public NeuralTopoGraph(Scanner scanner) {
		super(scanner);
		// TODO Auto-generated constructor stub
	}

	public static int MAX_LEVEL = 6;
	public static int MIN_LEVEL = 3;
	public static int MAX_COMP_PER_LEVEL = 5;
	public static int MIN_COMP_PER_LEVEL = 2;
	public static int MAX_EXE_PER_COMP = 3;
	public static int MIN_EXE_PER_COMP = 1;
//	public static int MAX_LEVEL = 3;
//	public static int MIN_LEVEL = 2;
//	public static int MAX_COMP_PER_LEVEL = 3;
//	public static int MIN_COMP_PER_LEVEL = 1;
//	public static int MAX_EXE_PER_COMP = 3;
//	public static int MIN_EXE_PER_COMP = 1;
	
	public static Graph genNeuralTopoGraph(){
		Random random = new Random(System.currentTimeMillis());
		int level = random.nextInt(MAX_LEVEL - MIN_LEVEL) + MIN_LEVEL;
		ArrayList<ArrayList<ArrayList<Integer>>> graphArray = 
				new ArrayList<ArrayList<ArrayList<Integer>>>(); 
		//TODO
		int exeNum = 0;
		for (int i = 0; i < level; i++) {
			graphArray.add(new ArrayList<ArrayList<Integer>>());
			int compPerLevel = random.nextInt(MAX_COMP_PER_LEVEL 
					- MIN_COMP_PER_LEVEL) + MIN_COMP_PER_LEVEL;
			ArrayList<ArrayList<Integer>> compArray = graphArray.get(i);
			for (int j = 0; j < compPerLevel; j ++){
				int exePerComp = random.nextInt(MAX_EXE_PER_COMP 
						- MIN_EXE_PER_COMP) + MIN_EXE_PER_COMP;
				ArrayList<Integer> exeArray = new ArrayList<Integer>();
				for (int k = 0; k < exePerComp; k++) {
					exeArray.add(exeNum);
					exeNum ++;
				}
				compArray.add(exeArray);
			}
		}
		int eCount = 0;
		double[][] weight = new double[exeNum][exeNum];
		for (int i = 0; i < exeNum; i++) {
			Arrays.fill(weight[i], -1);
			weight[i][i] = 0;
		}
		ArrayList<Integer> nextLevelConn = new ArrayList<Integer>();
		for (ArrayList<ArrayList<Integer>> currentLevelComp : graphArray) {
			int i = graphArray.indexOf(currentLevelComp);
			if (nextLevelConn.size() != currentLevelComp.size()) {
				//first level
				if (nextLevelConn.size() == 0);
				//last level components choosed less than the current level count
				else {
					for (int j = 0; j < currentLevelComp.size(); j++) {
						int last = i - 1;
						ArrayList<ArrayList<Integer>> lastLevelComp = graphArray.get(last);
						
						if (!nextLevelConn.contains(j)) {
							int randLastComp = lastLevelComp.size() == 1 ? 0 : random.nextInt(lastLevelComp.size() - 1);
							ArrayList<Integer> curPerCompExe = currentLevelComp.get(j);
							ArrayList<Integer> lastPerCompExe = lastLevelComp.get(randLastComp);
							for (Integer lastIndex : lastPerCompExe) {
								for (Integer curIndex : curPerCompExe) {
									//range from 0 to 10
									double tmpWeight = 1.0 + 10 * random.nextDouble();
									//round the precision
									tmpWeight = Utils.round(tmpWeight, 1);
									if (weight[curIndex][lastIndex] != -1) {
										System.out.println("Last Level: " + last);
										System.out.println("Last level Index: " + lastIndex);
										System.out.println("Cur level Index: " + curIndex);
									}
									weight[curIndex][lastIndex] = tmpWeight;
									weight[lastIndex][curIndex] = tmpWeight;
									eCount ++;
								}
							}
						}
					}
				}
			}
			nextLevelConn.clear();
			//connect current level to next level
			if (i + 1 == graphArray.size()) {
				break;
			}
			ArrayList<ArrayList<Integer>> nextLevelComp = graphArray.get(i + 1);
			//iterate all the component in current level
			for (int j = 0; j < currentLevelComp.size(); j++) {
				//get a random component in the next level
				int randComp = nextLevelComp.size() == 1 ? 0 : random.nextInt(nextLevelComp.size() - 1);
				ArrayList<Integer> exeInRandCom = nextLevelComp.get(randComp);
				ArrayList<Integer> exeInCurComp = currentLevelComp.get(j);
				for (int k = 0; k < exeInCurComp.size(); k++) {
					for (int k2 = 0; k2 < exeInRandCom.size(); k2++) {
						//range from 0 to 10
						double tmpWeight = 1.0 + 10 * random.nextDouble();
						//round the precision
						tmpWeight = Utils.round(tmpWeight, 1);
						
						int randNextIndex = exeInRandCom.get(k2), curIndex = exeInCurComp.get(k);
						
						if (weight[randNextIndex][curIndex] != -1) {
							System.out.println("Next Level: " + (i + 1));
							System.out.println("Next level Index: " + randNextIndex);
							System.out.println("Cur level Index: " + curIndex);
						}
						weight[curIndex][randNextIndex] = tmpWeight;
						weight[randNextIndex][curIndex] = tmpWeight;
						eCount ++;
					}
				}
				if (!nextLevelConn.contains(randComp)) {
					nextLevelConn.add(randComp);
				}
			}
		}
//		System.out.println("Level Details:");
//		for (int j = 0; j < graphArray.size(); j++) {
//			System.out.print("Level " + j + ": ");
//			ArrayList<ArrayList<Integer>> levelComp = graphArray.get(j);
//			for (int j2 = 0; j2 < levelComp.size(); j2++) {
//				System.out.print("Component " + j2 + ": ");
//				ArrayList<Integer> compExe = levelComp.get(j2);
//				for (int k = 0; k < compExe.size(); k++) {
//					System.out.print(compExe.get(k) + " ");
//				}
//			}
//			System.out.print("\n");
//		}
		Graph graph = new Graph(exeNum, eCount);
		graph.setWeight(weight);
		return graph;
	}
	
	public static void main(String[] args){
		int maxDevCount = 15;
		for (int i = 0; i < 20; i++) {
			System.out.println("==================Round " + i);
			Graph neuralTopoGraph = NeuralTopoGraph.genNeuralTopoGraph();
			Graph devGraph = Graph.genStrongConnGraph(maxDevCount);
			//1.25 sets the current total executor number is 1.25 times task number
			int actualDevCount = devGraph.getvCount(), totalExe = (int)(neuralTopoGraph.getvCount() * 2);
			int[] devExeArray = Utils.randNumFixedSum(actualDevCount, totalExe);
			
			try {
				//MATLEB test cases
				FileWriter fileWriterML = new FileWriter(".\\testcases\\ml-testcase" + i);
				//Greedy algorithm test cases
				FileWriter fileWriterG = new FileWriter(".\\testcases\\g-testcase" + i);
				
				neuralTopoGraph.writeMatrixGraph(fileWriterML);
				devGraph.writeMatrixGraph(fileWriterML);
				Utils.writeDevExecutors(devExeArray, fileWriterML);
				
				neuralTopoGraph.writeAdjGraph(fileWriterG);
				devGraph.writeAdjGraph(fileWriterG);
				Utils.writeDevExecutors(devExeArray, fileWriterG);
				
				fileWriterG.close();
				fileWriterML.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
