package algo.sorts;

import java.util.Random;

/**
 * @author bingg
 * @Date 2020-04-27
 * @Time 09:53
 * @Descrption 选择排序
 */
public class SelectionSort {

	/**
	 * 选择排序
	 * 每次选择待排序数组中的最小值插入到排序数组尾部
	 * 为了防止不必要的数据移动，直接交换数据即可
	 * @param a
	 */
	public static void selectionSort(int[] a){
		//边界值考虑
		if(a.length <= 1){
			return;
		}
		for (int i = 0; i < a.length; i++){
			//最小值的下标索引
			int index = i;
			for (int j = i + 1; j < a.length; j ++){
				if(a[j] < a[index]){
					index = j;
				}
			}
			swap(a, i, index);
		}
	}


	/**
	 * 交换数组a中i和j位置的数据
	 * @param a 数组
	 * @param i 位置i
	 * @param j 位置j
	 */
	private static void swap(int[] a, int i, int j){
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String[] args) {
		int a[] = new int[10];
		Random random = new Random();
		System.out.println("sort before:");
		for (int i = 0; i < 10; i++) {
			a[i] = random.nextInt(90) + 10;
			System.out.print(a[i] + ",");
		}
		selectionSort(a);
		System.out.println("\nsort after:");
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + ",");
		}
	}
}
