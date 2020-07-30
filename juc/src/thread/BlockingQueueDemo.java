package thread;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author bingg
 * @Date 2020-07-15
 * @Time 15:11
 * @Descrption BlockingQueueDemo
 */
public class BlockingQueueDemo {

	private int size = 20;
	private LinkedBlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(size);

	class Producer extends Thread{
		@Override
		public void run() {
			while (true){
				try {
					blockingQueue.put(1);
					System.out.println("1队列剩余空间：" + (size - blockingQueue.size()));
				}catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		}
	}

	class Consumer extends Thread{
		@Override
		public void run() {
			while (true){
				try {
					blockingQueue.take();
					System.out.println("2队列剩余" + blockingQueue.size() + "个元素");
				}catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		BlockingQueueDemo blockingQueueDemo = new BlockingQueueDemo();
		Producer producer = blockingQueueDemo.new Producer();
		Consumer consumer = blockingQueueDemo.new Consumer();
		producer.start();
		consumer.start();
	}
}
