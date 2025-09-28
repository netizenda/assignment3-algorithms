import java.util.*;

public class MergeSort {
    private static final int INSERTION_SORT_THRESHOLD = 16;

    public static void sort(int[] arr) {
        int[] buffer = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, buffer);
    }

    private static void mergeSort(int[] arr, int left, int right, int[] buffer) {
        if (right - left + 1 <= INSERTION_SORT_THRESHOLD) {
            insertionSort(arr, left, right);
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(arr, left, mid, buffer);
        mergeSort(arr, mid + 1, right, buffer);
        merge(arr, left, mid, right, buffer);
    }

    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] buffer) {
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) buffer[k++] = arr[i++];
            else buffer[k++] = arr[j++];
        }
        while (i <= mid) buffer[k++] = arr[i++];
        while (j <= right) buffer[k++] = arr[j++];
        for (i = left; i <= right; i++) arr[i] = buffer[i];
    }
}
