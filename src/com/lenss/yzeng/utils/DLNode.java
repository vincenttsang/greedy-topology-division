package com.lenss.yzeng.utils;

public class DLNode <T extends Sortable> {
	private DLNode<T> parent;
	private DLNode<T> next;
	private T value;
	
	public DLNode(DLNode<T> parent, T value, DLNode<T> next) {
		this.parent = parent;
		this.next = next;
		this.value = value;
	}
	
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
}
