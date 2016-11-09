package com.lenss.yzeng.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Utils {
	public static int[] randNumFixedSum(int length, int sum){
		int[] lineSegment = new int[length + 1];
		int[] randomNum = new int[length];
		lineSegment[0] = 0;
		lineSegment[1] = sum;
		Random random = new Random(System.currentTimeMillis());
		for (int i = 2; i < lineSegment.length; i++) {
			lineSegment[i] = random.nextInt(sum);
		}
		Arrays.sort(lineSegment);
		for (int i = 0; i < randomNum.length; i++) {
			randomNum[i] = lineSegment[i + 1] - lineSegment[i];
		}
		return randomNum;
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
