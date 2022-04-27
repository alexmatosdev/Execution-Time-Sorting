

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
 * Class: CMSC214 
 * Instructor: Cristopher Fallon
 * Date: 3/4/2022
 * Description: This class uses File to decrypt a previously encrypted class
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: Alex Matos
*/

public class ExecuitionTimeSorting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] fiftyArr = new int[50000];
		int[] hundredArr = new int[100000];
		int[] hundredFiftyArr = new int[150000];
		int[] twoHundredArr = new int[200000];
		int[] twoHundredFiftyArr = new int[250000];
		int[] threeHundredArr = new int[300000];
		int[] twoMillion = new int[2000000];
		int[] threeMillion = new int[3000000];
		int[] fourMillion = new int[4000000];
		int[] fiveMillion = new int[5000000];
		int[] testArr = new int[8];
		
		List<int[]> list = Arrays.asList(fiftyArr, hundredArr, hundredFiftyArr, twoHundredArr, twoHundredFiftyArr, threeHundredArr, twoMillion, threeMillion, fourMillion, fiveMillion, testArr);
		createTable();
		Random rand = new Random();
		for (int[] x : list)
			for (int i = 0; i < x.length; i++) {
				x[i] = rand.nextInt(100);
			}
		for (int[] x : list)
			fillTable(x);
		
	}
	
	public static void fillTable(int[] fiftyArr) {
		long startTime;
		long endTime;
		long executionTime;
		
		System.out.print(fiftyArr.length + "\t\t|"); startTime = System.currentTimeMillis();selectionSort(fiftyArr); endTime = System.currentTimeMillis(); executionTime = endTime - startTime;
		System.out.print("\t"+executionTime);
		startTime = System.currentTimeMillis(); radixSort(fiftyArr); endTime = System.currentTimeMillis(); executionTime = endTime - startTime;
		System.out.print("\t\t"+executionTime);
		startTime = System.currentTimeMillis(); bubbleSort(fiftyArr); endTime = System.currentTimeMillis(); executionTime = endTime - startTime;
		System.out.print("\t\t"+executionTime);
		int[] x = Arrays.copyOf(fiftyArr, fiftyArr.length);
		startTime = System.currentTimeMillis(); mergeSort(x); endTime = System.currentTimeMillis(); executionTime = endTime - startTime;
		System.out.print("\t\t"+executionTime);
		x = Arrays.copyOf(fiftyArr, fiftyArr.length);
		startTime = System.currentTimeMillis(); quickSort(x,0,x.length-1); endTime = System.currentTimeMillis(); executionTime = endTime - startTime;
		System.out.print("\t\t"+executionTime);
		x = Arrays.copyOf(fiftyArr, fiftyArr.length);
		startTime = System.currentTimeMillis(); heapSort(x); endTime = System.currentTimeMillis(); executionTime = endTime - startTime;
		System.out.print("\t\t"+executionTime + "\n");
	}

	public static void createTable() {
		System.out.println("Arraysize\t|\tSeletion Sort\tRadix Sort\tBubble Sort\tMerge Sort\tQuick Sort\tHeap Sort");
		//System.out.println("____________________________________________________________________________________________________________________");
	}
	
	public static int[] selectionSort(int[] arr) {
		int min; int index = 0;
		int[] arr2 = Arrays.copyOf(arr, arr.length);
		int[] subArr = new int[arr2.length];
		for (int i = 0; i < arr2.length; i++) {
			min = 101;
			for (int j = 0; j < arr2.length; j++) {
				if (arr2[j] < min) {
					min = arr2[j];
					index = j;
				}
			}
			arr2[index] = 101;
			subArr[i] = min;
		}
		return subArr;
	}

	public static int[] bubbleSort(int[] a) {
		int[] arr = Arrays.copyOf(a, a.length);
		for (int i = 0; i < arr.length; i++) {
			for (int j = 1; j < arr.length; j++) {
				if (arr[j] < arr[j-1]) {
					int t = arr[j];
					arr[j] = arr[j-1];
					arr[j-1] = t;
				}
			}
		}
		//for (int i = 0; i < arr.length; i++)
		//System.out.print(" " + arr[i]);
		return arr;
	}

	public static int[] mergeSort(int[] arr) {
		//int[]arr =Arrays.copyOf(ar, ar.length);
		int index = 0;
		if (arr.length > 1) {
			int[]fh = new int[arr.length/2];
			for (int i = 0; i < arr.length/2;i++)
				fh[i] = arr[i];
			mergeSort(fh);
			int[] sh = new int[arr.length - arr.length / 2];
			for (int i = arr.length/2; i < arr.length; i++) {
				sh[index] = arr[i];
				index++;
			}
			mergeSort(sh);
			merge(fh,sh,arr);
		}
		return arr;
	}

	// Merges the first and second half
	public static void merge(int[] fh, int[] sh, int[] arr) {
		// TODO Auto-generated method stub
		int i = 0, j = 0, t= 0, length = fh.length;
		while (i < length && j < length) {
			if (fh[i] < sh[j]) {
				arr[t] = fh[i]; t++; i++;
			} else {
				arr[t] = sh[j]; t++; j++;
			}
		}
		while (i < length) {
			arr[t] = fh[i]; t++; i++;
		}
		while (j < length) {
			arr[t] = sh[j]; t++; j++;
		}
	}

	public static int[] quickSort(int[] arr, int f, int l) {
		if (l > f) {
			int pi = partition(arr,f,l);
			quickSort(arr,f,pi-1);
			quickSort(arr,pi+1,l);
		}
		
		return arr;
	}

	// Finds numbers bigger and  smaller than pivot and swaps them
	private static int partition(int[] arr, int f, int l) {
		// TODO Auto-generated method stub
		int p = arr[f], li = f + 1, hi = l;
		while (hi > li) {
			while (li <= hi && arr[li] <= p)
				li++;
			while (li <= hi && arr[hi] > p)
				hi--;

			if (hi > li) {
				//Collections.swap(arr, arr[right], arr[left]);
				int t = arr[hi];
				arr[hi] = arr[li];
				arr[li] = t;
			}
		}
		while (hi > f && arr[hi] >= p)
			hi--;
		if (p > arr[hi]) {
			arr[f] = arr[hi];
			arr[hi] = p;
			return hi;
		}
		else {
			return f;
		}
	}

	public static int[] radixSort(int[] a) {
		int max = -1;
		int passed=0;
		int[] arr = Arrays.copyOf(a, a.length);
		for (int x : arr)
			if (x > max)
				max= new Integer(x);
		while (max != 0) {
			ArrayList<Integer>[] bucket = new ArrayList[10];
			for (int i = 0; i < 10; i++) 
				bucket[i] = new ArrayList<>();
			for (int i = 0; i < arr.length; i++) {
				if (passed == 0) {
					int bi = arr[i]%10;
					bucket[bi].add(arr[i]);
				}
				else {
					int bi = arr[i]/10;
					bucket[bi].add(arr[i]);
				}
			}
			int index = 0;
			for (int i = 0; i < bucket.length;i++)
				for (int j = 0;j < bucket[i].size(); j++)
					if (bucket[i].get(j) != null)
						arr[index++] = bucket[i].get(j);
			max /= 10;
			passed++;
		}
		return arr;
	}

	// This merthod organizes a heap so that the parent is always smaller.
	// Heap goes from smallest to largest
	public static int[] heapSort(int[] arr) {
		
		int parentNode;
		for (int j = 0; j < arr.length;j++) {
			int ex = 0;
			for (int i = 0; i < arr.length; i++) {
				parentNode = arr[(i-1)/2];
				if (parentNode > arr[i]) {
					int t = parentNode;
					arr[(i-1)/2] = arr[i];
					arr[i] = t;
					ex++;
				}
			}
			if (ex == 0)
				break;
		}
		return arr;
	}
}
