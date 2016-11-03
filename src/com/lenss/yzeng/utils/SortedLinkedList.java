package com.lenss.yzeng.utils;

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


