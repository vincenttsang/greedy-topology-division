package com.lenss.yzeng;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.Buffer;
import java.nio.file.FileAlreadyExistsException;
import java.util.LinkedList;
import java.util.Scanner;

public class Graph {
	private static int vCount;
	private static int eCount;
	
	private double weight[][];
	private LinkedList<Edge> edgeList;
	
	public class SortedLinkedList<T extends Sortable>{
		T value;

		SortedLinkedList<T> next;
		SortedLinkedList<T> parent;
		
		public SortedLinkedList(T value) {
			// TODO Auto-generated constructor stub
			this.value = value;
			next = null;
			parent = null;
		}
		
		public void ascendingInsert(T element){
			SortedLinkedList<T> current = this;
			SortedLinkedList<T> parent = null;
			
			//if it is bigger than the root node
			if (current.getValue().getKey() < element.getKey()) {
				SortedLinkedList<T> tempParent = current.getParent();
				SortedLinkedList<T> tempNext = current.getNext();
				
				SortedLinkedList<Sortable>
				current.parent = element;
			}
			
			while (current != null) {
				if (current.getValue().getKey() < element.getKey()) {
					
				}
				parent = current
			}
		}

		public T getValue() {
			return value;
		}

		public void setValue(T value) {
			this.value = value;
		}
		
		public void setParent(SortedLinkedList<T> parent){
			this.parent = parent;
		}
		
		public void setNext(SortedLinkedList<T> next){
			this.next = next;
		}
		
		public SortedLinkedList<T> getNext() {
			return next;
		}

		public SortedLinkedList<T> getParent() {
			return parent;
		}
	}
	
	public interface Sortable{
		public int getKey();
	}
	
	public class Edge implements Sortable{
		private int v;
		private int w;
		private int weight;
		
		public Edge(int v, int w) {
			// TODO Auto-generated constructor stub
			this.v = v;
			this.w = w;
		}

		public int getV() {
			return v;
		}

		public void setV(int v) {
			this.v = v;
		}

		public int getW() {
			return w;
		}

		public void setW(int w) {
			this.w = w;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		@Override
		public int getKey() {
			// TODO Auto-generated method stub
			return this.weight;
		}
	}

	public Graph(int vCount, int eCount){
		this.vCount = vCount;
		this.eCount = eCount;
		weight = new double[vCount][vCount];
		this.edgeList = new LinkedList<Edge>();
	}
	
	public Graph(Scanner scanner){
		this(scanner.nextInt(), scanner.nextInt());
		if (this.eCount < 0 || this.vCount < 0) {
			throw new IllegalArgumentException("Number of edges or vertices cannot be negative!");
		}
		for (int i = 0; i < this.eCount; i++) {
			int v = scanner.nextInt();
			int w = scanner.nextInt();
			double weight = scanner.nextDouble();
			this.weight[v][w] = weight;
			edgeList.
			
		}
	}
	
	public static Graph graphInit(String filePath) throws FileNotFoundException{
		Scanner scanner = new Scanner(new File(filePath));
		return (new Graph(scanner));
	}
}
