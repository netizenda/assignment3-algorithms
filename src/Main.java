import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] arr = {55, 21, 1, 7, 10, 14, 8, 2, 6};

        // MergeSort
        MergeSort.sort(arr);
        System.out.println("MergeSort: " + Arrays.toString(arr));

        // QuickSort
        arr = new int[]{87, 99, 54, 20, 5, 4, 65, 98, 13};
        QuickSort.sort(arr);
        System.out.println("QuickSort: " + Arrays.toString(arr));

        // Deterministic Select
        arr = new int[]{9, 3, 7, 1, 5, 4, 8, 2, 6};
        int k = 5;
        System.out.println("Deterministic Select (" + k + "th smallest): " + DeterministicSelect.select(arr, k));

        // Closest Pair
        ClosestPair.Point[] pts = {
                new ClosestPair.Point(2, 3), new ClosestPair.Point(12, 30),
                new ClosestPair.Point(40, 50), new ClosestPair.Point(5, 1),
                new ClosestPair.Point(12, 10), new ClosestPair.Point(3, 4)
        };
        System.out.println("Closest Pair Distance: " + ClosestPair.closestPair(pts));
    }
}
