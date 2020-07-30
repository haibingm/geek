package algo.linkedlist;

import java.util.Random;

/**
 * @author bingg
 * @Date 2020-04-28
 * @Time 10:06
 * @Descrption 单链表操作
 */
public class SingleLinkedList {

	//定义一个头结点始终指向链表的第一个结点
	private SNode head = null;

	/**
	 * 找到结点值为value的结点
	 * @param value
	 * @return
	 */
	public SNode find(int value){
		SNode p = head;
		while (p!= null && p.getData() != value){
			p = p.getNext();
		}
		return p;
	}


	/**
	 * 尾插法,在链表尾部进行插入结点
	 * @param value
	 * @return
	 */
	public SNode insertToTail(int value){
		SNode node = new SNode(value, null);
		if(head == null){
			head = node;
		}else {
			SNode p = head;
			while (p.getNext()!=null){
				p = p.getNext();
			}
			p.setNext(node);
		}
		return head;
	}

	/**
	 * 头插法，在链表头部插入新结点
	 * @param value
	 * @return
	 */
	public SNode insertToHead(int value){
		SNode node = new SNode(value, null);
		if(head == null) {
			head = node;
		}else {
			node.setNext(head);
			head = node;
		}
		return head;
	}

	/**
	 * 在p结点前添加值为value的结点
	 * @param p
	 * @param value
	 * @return
	 */
	public SNode insertBefore(SNode p, int value){
		SNode node = new SNode(value, null);
		if(p == null){
			return head;
		}
		if(p == head){
			return insertToHead(value);
		}
		SNode q = head;
		//找到p结点的前继结点
		while (q.getNext() != null && q.getNext() != p){
			q = q.getNext();
		}
		node.setNext(p);
		q.setNext(node);
		return head;
	}

	/**
	 * 在一个结点后插入值为value的结点
	 * @param p
	 * @param value
	 * @return
	 */
	public SNode insertAfter(SNode p, int value){
		SNode node = new SNode(value, null);
		if(p == null){
			return head;
		}
		if(p.getNext() == null){
			return insertToTail(value);
		}
		node.setNext(p.getNext());
		p.setNext(node);
		return head;
	}

	/**
	 * 删除值为value的结点
	 * @param value
	 * @return
	 */
	public SNode deleteByValue(int value){
		if (head == null){
			return head;
		}
		SNode p = head;
		SNode q = null;
		//找到值为value的结点及其前驱结点
		while (p != null && p.getData() != value){
			q = p;
			p = p.getNext();
		}

		if(p == null){
			return head;
		}
		if(q == null){
			head = head.getNext();
			return head;
		}
		q.setNext(q.getNext().getNext());
		return head;
	}

	/**
	 * 反转链表
	 * @param p
	 * @return
	 */
	public SNode reverse(SNode p){
		SNode pre = null;
		SNode cur = p;
		while (cur != null){
			SNode next = cur.getNext();
			cur.setNext(pre);
			pre = cur;
			cur = next;
		}
		head = pre;
		return head;
	}


	public void print(){
		SNode p = head;
		while (p != null){
			System.out.print(p.getData() + " ");
			p = p.getNext();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		SingleLinkedList link = new SingleLinkedList();
		Random random = new Random();
		for (int i = 0; i< 10; i++){
			int data = random.nextInt(90) + 10;
			//System.out.print(data + " ");
			//link.insertToHead(data);
			link.insertToTail(data);

		}
		System.out.println();
		link.print();
		link.reverse(link.head);
		link.print();
	}

}
