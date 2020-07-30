package thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author bingg
 * @Date 2020-07-17
 * @Time 10:20
 * @Descrption
 */
public class LockDemo {
	public static void main(String[] args) {
		Window window = new Window();
		Thread thread1 = new Thread(window, "thread1");
		Thread thread2 = new Thread(window, "thread2");
		Thread thread3 = new Thread(window, "thread3");
		thread1.start();
		thread2.start();
		thread3.start();
	}

}

class Window implements Runnable{

	private volatile int num = 100;
	ReentrantLock lock = new ReentrantLock(true);

	@Override
	public void run() {
		while (true){
			lock.lock();
			try {
				if(num > 0){
					System.out.println(Thread.currentThread().getName()+"窗口在售票,票号为"+ num);
					num --;
				}else {
					break;
				}
			}finally {
				lock.unlock();
			}
		}
	}
}