package algo.sorts;

import java.util.Random;

/**
 * @author bingg
 * @Date 2020-04-26
 * @Time 21:39
 * @Descrption 插入排序
 */
public class InsertionSort {

	/**
	 * 插入排序，将数据分为已排序部分和未排序部分
	 * 每次从未排序部分取数据，从尾至头比较已排序部分
	 * 找到插入位置后，数据先后移一位，然后将带插入的数据插入到指定位置
	 * 选取第一个元素为已排序数组元素
	 * 23,50,39,20,62
	 * 第一次插入 50 > 23 已排序区域为23,50  未排序区域为39,20,62
	 * 第二次插入 39 < 50 50后移一位，39 > 23 将39插入到第2位 已排序区域为23,39,50  未排序区域为20,62
	 * 第三次插入 20 < 50 20 < 39 20 < 23 将23,39,50集体向后移一位，将20插入第一位 已排序区域为20,23,39,50  未排序区域为62
	 * 第四次插入 62 > 50 排序结束
	 *
	 * @param a
	 */
	public static void insertionSort(int[] a){
		//边界值考虑
		if(a.length <= 1){
			return;
		}
		for (int i = 1; i< a.length ; i++){
			int j = i -1;
			int value = a[i];
			while (j >= 0 && value < a[j]){
				a[j + 1] = a[j];
				j -- ;
			}
			a[j + 1] = value;
		}
	}

	public static void main(String[] args) {
		int a[] = new int[10];
		Random random = new Random();
		System.out.println("sort before:");
		for (int i = 0; i < 10; i++) {
			a[i] = random.nextInt(90) + 10;
			System.out.print(a[i] + ",");
		}
		insertionSort(a);
		System.out.println("\nsort after:");
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + ",");
		}
	}

}
