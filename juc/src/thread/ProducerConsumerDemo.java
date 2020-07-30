package thread;

import java.util.LinkedList;

/**
 * @author bingg
 * @Date 2020-07-30
 * @Time 11:48
 * @Descrption
 */
public class ProducerConsumerDemo<T> {

	final private LinkedList<T> list = new LinkedList<>();
	final private int MAX = 10;
	private int count = 0;

	public synchronized void put(T t){
		while (list.size() == MAX){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		list.add(t);
		count++;
		this.notifyAll();
	}

	public synchronized T get(){
		T t = null;
		while (list.size() == 0){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		t = list.removeFirst();
		count --;
		this.notifyAll();
		return t;
	}

	public static void main(String[] args) {
		ProducerConsumerDemo<String> c = new ProducerConsumerDemo<>();
		//启动消费者
		for (int i = 0; i < 10; i++) {
			new Thread(() ->{
				for (int j = 0; j < 5; j++) {
					System.out.println(c.get());
				}
			}, "c" + i).start();
		}

		//启动生产者
		for (int i = 0; i < 2; i++){
			new Thread(() ->{
				for (int j = 0; j < 25; j++) {
					c.put(Thread.currentThread().getName() + " " + j);
				}
			}, "p" + i).start();
		}
	}
}
