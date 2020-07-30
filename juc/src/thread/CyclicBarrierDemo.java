package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author bingg
 * @Date 2020-07-21
 * @Time 15:22
 * @Descrption
 */
public class CyclicBarrierDemo {
	public static void main(String[] args) throws InterruptedException{
		CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
			@Override
			public void run() {
				System.out.println("运动员到齐，开始跑");
			}
		});

		List<Thread> threads = new ArrayList<>();
		for (int i = 0; i < 10; i++){
			Thread thread = new Thread(new Prepare(cyclicBarrier), "[第" + i + "运动员]");
			threads.add(thread);
			Thread.sleep(1000);
			thread.start();
//			if (i == 3) {
//				thread.interrupt();
//			}
		}
		Thread.sleep(1000);
		System.out.println("栅栏损坏" + cyclicBarrier.isBroken());
	}
}

class Prepare implements Runnable{

	private CyclicBarrier barrier;

	public Prepare(CyclicBarrier barrier) {
		this.barrier = barrier;
	}

	@Override
	public void run() {
		try {
			System.out.println(Thread.currentThread().getName() + "准备完成");
			barrier.await();
		}catch (InterruptedException e){
			System.out.println(Thread.currentThread().getName() + "中断");
		}catch (BrokenBarrierException e){
			System.out.println(Thread.currentThread().getName() + "BrokenBarrierException");
			barrier.reset();
		}
	}
}
