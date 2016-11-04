package com.lenss.yzeng.utils;

public class SortedLinkedList<T extends Sortable>{
	DLNode<T> start;
	
	public SortedLinkedList(T element) {
		// TODO Auto-generated constructor stub
		this.start = new DLNode<T>(element);
	}
	
	public void descendingInsert(T element){
		//if the start node is smaller than the node to insert
		DLNode<T> current = this.start;
		DLNode<T> newNode;
		double elementKey = element.getKey();
		if (current.getValue().getKey() < elementKey) {
			//
			newNode = new DLNode<T>(element, current);
			current.setParent(newNode);
			this.start = newNode;
			return;
		}
		//else, traverse the list
		while (current.hasNext()) {
			current = current.getNext();
			if (current.getValue().getKey() < elementKey) {
				DLNode<T> parent = current.getParent();
				newNode = new DLNode<T>(parent, element, current);
				parent.setNext(newNode);
				current.setParent(newNode);
				return;
			}
		} 
		
		newNode = new DLNode<T>(current, element);
		current.setNext(newNode);
		return;
	}

	public void print(){
		DLNode<T> current = this.start;
		while (current.hasNext()) {
			System.out.print(current.getValue().getKey() + "->");
			current = current.getNext();
		}
		System.out.print(current.getValue().getKey());
	}

	public DLNode<T> getStart() {
		return start;
	}

	public void setStart(DLNode<T> start) {
		this.start = start;
	}
}


