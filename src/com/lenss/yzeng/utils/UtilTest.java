package com.lenss.yzeng.utils;

import java.util.Random;

class MyInt implements Sortable{
	private int key;
	
	public MyInt(int key) {
		// TODO Auto-generated constructor stub
		this.key = key;
	}
	
	@Override
	public int getKey() {
		// TODO Auto-generated method stub
		return this.key;
	}
}

public class UtilTest {
	public static void main(String args[]){
		MyInt test = new MyInt(0);
		SortedLinkedList<MyInt> linkedList = new SortedLinkedList<MyInt>(test);
		Random random = new Random();
		
		for (int i = 1; i < 10; i++) {
			int randNumber = random.nextInt(100);
			MyInt newInt = new MyInt(randNumber);
			linkedList.descendingInsert(newInt);
		}
		linkedList.print();
	}
	
}
