package thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author bingg
 * @Date 2020-07-21
 * @Time 14:37
 * @Descrption
 */
public class CountDownLatchDemo {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		CountDownLatch countDownLatch = new CountDownLatch(3);
		Worker w1 = new Worker(countDownLatch,"张三");
		Worker w2 = new Worker(countDownLatch,"李四");
		Worker w3 = new Worker(countDownLatch,"王五");

		BossWork boss = new BossWork(countDownLatch);

		executor.execute(boss);
		executor.execute(w3);
		executor.execute(w2);
		executor.execute(w1);
		executor.shutdown();
	}
}

class Worker implements Runnable{

	private CountDownLatch countDownLatch;
	private String name;

	public Worker(CountDownLatch countDownLatch, String name) {
		this.countDownLatch = countDownLatch;
		this.name = name;
	}

	@Override
	public void run() {
		this.doWork();
		try {
			TimeUnit.SECONDS.sleep(new Random().nextInt(10));
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		System.out.println(this.name + "-结束-工作！");
		countDownLatch.countDown();
	}

	private void doWork(){
		System.out.println(this.name + "-开始-干活!");
	}
}

class BossWork implements Runnable{

	private CountDownLatch countDownLatch;

	public BossWork(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		System.out.println("老板等待所有的工人干完活......");
		try {
			this.countDownLatch.await();
		} catch (InterruptedException e) {
		}
		System.out.println("工人活都干完了，老板开始检查了！");
	}
}
