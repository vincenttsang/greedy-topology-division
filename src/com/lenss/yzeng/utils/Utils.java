package com.lenss.yzeng.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Utils {
	//now the function support no 0 value in the produced sequence
	public static int[] randNumFixedSum(int length, int sum){
		//reconstructing this method using arraylist to store the line
		int[] lineSegment = new int[length + 1];
		int[] randomNum = new int[length];
		lineSegment[0] = 0;
		lineSegment[1] = sum;
		Random random = new Random(System.currentTimeMillis());
		for (int i = 2; i < lineSegment.length; i++) {
			int randNum = 0;
			do {
				randNum = random.nextInt(sum);
			} while (uniqueArrayContains(lineSegment, randNum));
			lineSegment[i] = randNum;
		}
		Arrays.sort(lineSegment);
		for (int i = 0; i < randomNum.length; i++) {
			randomNum[i] = lineSegment[i + 1] - lineSegment[i];
		}
		return randomNum;
	}
	
	
	public static boolean uniqueArrayContains(int[] array, int element){
		for (int i : array) {
			if (element == i) {
				return true;
			}
		}
		return false;
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
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	public static void writeDevExecutors(int[] devExeArray, FileWriter fileWriter) throws IOException{
		System.out.println("===============Writing Dev Executors=============");
		System.out.println(devExeArray.length);
		fileWriter.write(devExeArray.length + "\n");
		for (int dev : devExeArray) {
			System.out.print(dev + " ");
			fileWriter.write(dev + " ");
		}
		System.out.print("\n");
	}
}
