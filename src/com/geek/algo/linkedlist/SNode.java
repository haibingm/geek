package com.geek.algo.linkedlist;

/**
 * @author bingg
 * @Date 2020-04-28
 * @Time 09:58
 * @Descrption 单链表结点类
 */
public class SNode {

	/**
	 * 存储数据值
	 */
	private int data;

	/**
	 * 下一个结点
	 */
	private SNode next;

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public SNode getNext() {
		return next;
	}

	public void setNext(SNode next) {
		this.next = next;
	}

	public SNode(int data, SNode next) {
		this.data = data;
		this.next = next;
	}
}
