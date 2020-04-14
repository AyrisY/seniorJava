package algorithm;

import java.util.Arrays;

/**
 * 快速排序
 * */
public class QuickSort {

    public static void sort(int[] arr, int startIndex, int endIndex) {
        if (arr == null) {
            return;
        }
        if (startIndex < 0 || endIndex <= startIndex) {
            return;
        }
        int pivot = arr[startIndex];
        int i = startIndex;
        int j = endIndex;
        while (i < j) {
            while (arr[j] >= pivot && i < j) {
                j--;
            }
            if (i != j) {
                arr[i] = arr[j];
            }
            while (arr[i] <= pivot && i < j) {
                i++;
            }
            if (i != j) {
                arr[j] = arr[i];
            }
        }
        arr[i] = pivot;
        if (i - 1 > startIndex) {
            sort(arr, startIndex, i - 1);
        }
        if (j + 1 < endIndex) {
            sort(arr, j + 1, endIndex);
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        Arrays.stream(arr).forEach(x -> {
            System.out.print(x + ",");
        });
        sort(arr, 0, arr.length - 1);
        System.out.println();
        Arrays.stream(arr).forEach(x -> {
            System.out.print(x + ",");
        });
    }
}
