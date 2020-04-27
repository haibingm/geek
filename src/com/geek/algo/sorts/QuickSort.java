package com.geek.algo.sorts;

import java.util.Random;

/**
 * @author bingg
 * @Date 2020-04-27
 * @Time 20:51
 * @Descrption 快排
 */
public class QuickSort {

	public static void quickSort(int[] a, int left , int right){
		//边界值考虑
		if(a.length <= 1){
			return;
		}
		if(left < right){
			int pivotIndex = partition(a, left, right);
			quickSort(a, left, pivotIndex - 1 < left ? left : pivotIndex - 1);
			quickSort(a, pivotIndex + 1 > right ? right : pivotIndex + 1, right);
		}
	}

	public static void quickSort2(int[]a, int left, int right){
		//边界值考虑
		if(a.length <= 1){
			return;
		}
		if(left > right){
			return;
		}
		int i = left;
		int j = right;
		int pivot = a[left];
		while (i < j){
			while (i<j && a[j] >= pivot){
				j--;
			}

			while (i<j && a[i] <= pivot){
				i++;
			}

			if(i<j){
				swap(a, i, j);
			}
		}
		a[left] = a[i];
		a[i] = pivot;
		quickSort2(a, left, j -1);
		quickSort2(a, i + 1 , right);
	}

	/**
	 * 分区函数
	 * 双指针法，start 和 end分别指向待排序数组头尾
	 * 选取分区点pivot为第一个元素或最后一个元素
	 * 若选取第一个元素则从尾部开始向前找到小于pivot的元素，然后从坐向右找到大于pivot的元素，交换
	 * 直到start和end相遇，则交换start或end与pivot未知的元素
	 * @param a
	 * @param left
	 * @param right
	 * @return
	 */
	private static int partition(int[]a, int left, int right){
		int pivot = a[right];
		int start = left;
		int end = right;
		while (start < end){
			while (start < end && a[start] <= pivot){
				start ++;
			}

			while (start < end && a[end] >= pivot){
				end --;
			}

			if (start < end) {
				swap(a, start, end);
			}
		}
		a[right] = a[start];
		a[start] = pivot;
		return start;
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
		quickSort(a, 0, a.length - 1);
		//quickSort2(a, 0, a.length - 1);

		System.out.println("\nsort after:");
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + ",");
		}
	}
}
