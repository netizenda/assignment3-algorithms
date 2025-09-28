public class DeterministicSelect {

    public static int select(int[] arr, int k) {
        return select(arr, 0, arr.length - 1, k);
    }

    private static int select(int[] arr, int left, int right, int k) {
        while (true) {
            if (left == right) return arr[left];

            int pivotIndex = medianOfMedians(arr, left, right);
            pivotIndex = partition(arr, left, right, pivotIndex);
            int length = pivotIndex - left + 1;

            if (k == length) return arr[pivotIndex];
            else if (k < length) right = pivotIndex - 1;
            else {
                k -= length;
                left = pivotIndex + 1;
            }
        }
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < pivot) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, right);
        return storeIndex;
    }

    private static int medianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;
        if (n < 5) {
            insertionSort(arr, left, right);
            return (left + right) / 2;
        }

        int numMedians = 0;
        for (int i = left; i <= right; i += 5) {
            int subRight = Math.min(i + 4, right);
            insertionSort(arr, i, subRight);
            int median = (i + subRight) / 2;
            swap(arr, left + numMedians, median);
            numMedians++;
        }
        return medianOfMedians(arr, left, left + numMedians - 1);
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

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
