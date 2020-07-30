package thread;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author bingg
 * @Date 2020-07-30
 * @Time 14:30
 * @Descrption
 */
public class ProducerConsumerDemo2<T> {

	final private LinkedList<T> list = new LinkedList<>();
	final private int MAX = 10;
	private int count = 0;

	private Lock lock = new ReentrantLock();
	private Condition producer = lock.newCondition();
	private Condition consumer = lock.newCondition();

	public void put(T t){
		try {
			lock.lock();
			while (list.size() == MAX){
				producer.await();
			}
			list.add(t);
			count++;
			consumer.signalAll();
		}catch (InterruptedException e){
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}

	public T get() {
		T t = null;
		try {
			lock.lock();
			while (list.size() == 0){
				consumer.await();
			}
			t = list.removeFirst();
			count--;
			producer.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		return t;
	}

	public static void main(String[] args) {
		ProducerConsumerDemo2<String> c = new ProducerConsumerDemo2<>();
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
