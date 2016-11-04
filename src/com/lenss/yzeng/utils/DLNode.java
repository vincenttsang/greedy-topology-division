package com.lenss.yzeng.utils;

public class DLNode <T extends Sortable> {
	private DLNode<T> parent;
	private DLNode<T> next;
	private T value;
	
	//get a node that is in the middle of doubly linked list
	public DLNode(DLNode<T> parent, T value, DLNode<T> next) {
		this(value);
		this.parent = parent;
		this.next = next;
	}
	
	//get a node that is in the head of the list
	public DLNode(T value, DLNode<T> next) {
		// TODO Auto-generated constructor stub
		this(value);
		this.parent = null;
		this.next = next;
	}
	
	//get a node that is in the tail of the list
	public DLNode(DLNode<T> parent, T value){
		this(value);
		this.parent = parent;
		this.next = null;
	}
	
	//get a basic node(just value, no parent and next)
	public DLNode(T value){
		this.parent = null;
		this.next = null;
		this.value = value;
	}
	
	public DLNode<T> getParent() {
		return parent;
	}
	
	public void setParent(DLNode<T> parent) {
		this.parent = parent;
	}
	
	public DLNode<T> getNext() {
		return next;
	}
	
	public void setNext(DLNode<T> next) {
		this.next = next;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	public boolean hasNext(){
		return (this.next == null ? false : true);
	}
}
