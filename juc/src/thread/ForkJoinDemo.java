package thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author bingg
 * @Date 2020-07-15
 * @Time 09:26
 * @Descrption ForkJoinDemo
 */
public class ForkJoinDemo {

	public static class CountTask extends RecursiveTask<Integer>{

		private static final int THRESHOLD = 2;
		private int start;
		private int end;

		public CountTask(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		protected Integer compute() {
			int sum = 0;
			boolean canCompute = (end - start) <= THRESHOLD;
			if(canCompute){
				for (int i = start; i <= end; i++){
					sum += i;
				}
			}else {
				int middle = (end + start) >> 1;
				CountTask leftTask = new CountTask(start, middle);
				CountTask rightTask = new CountTask(middle, end);
				leftTask.fork();
				rightTask.fork();
				int leftResult = leftTask.join();
				int rightResult = rightTask.join();
				sum = leftResult + rightResult;
			}
			return sum;
		}
	}

	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		CountTask task = new CountTask(1, 4);
		Future<Integer> result = forkJoinPool.submit(task);
		try {
			System.out.println(result.get());
		}catch (InterruptedException | ExecutionException e){

		}
	}
}
