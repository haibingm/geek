package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author bingg
 * @Date 2020-07-30
 * @Time 10:28
 * @Descrption LockSupportDemo
 */
public class LockSupportDemo {

	private List<Object> list = new ArrayList<>();

	public void add(Object o){
		list.add(o);
	}

	public int size(){return list.size();}

	public static Thread t1 = null, t2 = null;

	public static void main(String[] args) {

		LockSupportDemo lockSupportDemo = new LockSupportDemo();

		t2 = new Thread(() -> {
			//if (lockSupportDemo.size() != 5) {
				LockSupport.park();
			//}
			System.out.println("t2 结束");
			LockSupport.unpark(t1);
		}, "t2");

		t1 = new Thread(() ->{
			for (int i = 0; i < 10; i++) {
				lockSupportDemo.add(i);
				System.out.println("add" + i);
				if(lockSupportDemo.size() == 5){
					LockSupport.unpark(t2);
					LockSupport.park();
				}
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t1");

		t1.start();
		t2.start();

	}

}
