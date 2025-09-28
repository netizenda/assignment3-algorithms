import java.util.*;

public class QuickSort {
    public static void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1, new Random());
    }

    private static void quickSort(int[] arr, int left, int right, Random rand) {
        while (left < right) {
            int pivotIndex = left + rand.nextInt(right - left + 1);
            int pivot = arr[pivotIndex];
            int i = left, j = right;

            while (i <= j) {
                while (arr[i] < pivot) i++;
                while (arr[j] > pivot) j--;
                if (i <= j) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                    i++;
                    j--;
                }
            }

            if (j - left < right - i) {
                quickSort(arr, left, j, rand);
                left = i;
            } else {
                quickSort(arr, i, right, rand);
                right = j;
            }
        }
    }
}
