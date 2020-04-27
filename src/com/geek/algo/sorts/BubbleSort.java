package com.geek.algo.sorts;

import java.util.Random;

/**
 * @author bingg
 * @Date 2020-04-26
 * @Time 20:51
 * @Descrption 冒泡排序算法
 */
public class BubbleSort {


	/**
	 * 下沉。每次把最大的数移到当前待排序区域最后
	 * @param a
	 * @param type
	 */
	public static void bubbleSort(int[] a, int type) {
		//边界值考虑
		if(a.length <= 1){
			return;
		}
		for (int i = 0; i < a.length; i++) {
			//提前退出标志位
			boolean flag = false;
			for (int j = 0; j < a.length - i - 1; j++) {
				//升序排列
				if (type == 1) {
					if (a[j] > a[j + 1]) {
						swap(a, j, j + 1);
						flag = true;
					}
				} else {
					if (a[j] < a[j + 1]) {
						swap(a, j, j + 1);
						flag = true;
					}
				}
			}
			//若循环一次发现没有元素交换则已经有序
			if(!flag){
				break;
			}
		}
	}

	/**
	 * 上浮。每次把最小的数移到当前待排序区域最前
	 * @param a
	 * @param type
	 */
	public static void bubbleSort2(int[] a, int type) {
		//边界值考虑
		if(a.length <= 1){
			return;
		}
		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length ; j++) {
				//升序排列
				if (type == 1) {
					if (a[i] > a[j]) {
						swap(a, i, j);
					}
				} else {
					if (a[i] < a[j]) {
						swap(a, i, j);
					}
				}
			}
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
		bubbleSort(a,1);
		//bubbleSort2(a,2);
		System.out.println("\nsort after:");
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + ",");
		}
	}

}
