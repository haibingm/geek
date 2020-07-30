package thread;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author bingg
 * @Date 2020-07-17
 * @Time 15:43
 * @Descrption
 */
public class ReadWriteLockDemo {

	public static void main(String[] args) {
		final ReadWriteObject object = new ReadWriteObject();
		for (int i = 0; i < 5; i++) {
			new Thread() {
				@Override
				public void run() {
					while (true) {
						object.read();
					}
				}
			}.start();
			for (int j = 0; j < 5; j++) {
				new Thread() {
					@Override
					public void run() {
						while (true) {
							object.write(new Random().nextInt(10000));
						}
					}
				}.start();
			}
		}
	}
}

class ReadWriteObject {
	Object data = null;
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	public void read() {
		//上读锁，其他线程只能读不能写
		lock.readLock().lock();
		System.out.println(Thread.currentThread().getName() + "准备开始读取数据!");
		try {
			Thread.sleep((long) (Math.random() * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "读取完毕，data :" + data);
		lock.readLock().unlock();
	}

	public void write(Object data) {
		//上写锁，不允许其他线程读也不允许写
		lock.writeLock().lock();
		System.out.println(Thread.currentThread().getName() + " 准备写数据!");
		try {
			Thread.sleep((long) (Math.random() * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.data = data;
		System.out.println(Thread.currentThread().getName() + " have write data: " + data);
		lock.writeLock().unlock();
	}
}