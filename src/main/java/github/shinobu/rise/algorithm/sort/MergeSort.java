package github.shinobu.rise.algorithm.sort;

import java.util.Arrays;

/**
 * @author Shinobu
 * @since 2019/8/12
 */
public class MergeSort {

    private static int[] buf;

    public static void main(String[] args) {
        sort(new int[]{8, 8, 9, 7, 3, 1, 4, 9});
    }

    public static void sort(int[] arr) {
        buf = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void mergeSort(int[] arr, int l, int r) {
        if (arr == null || r <= l) {
            return;
        }
        
        int m = (r + l) / 2;
        mergeSort(arr, l, m);
        mergeSort(arr,  m+ 1, r);
        merge(arr, l, m, r);
    }

    private static void merge(int[] arr, int l, int m, int r) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int i = l, j = m + 1, k = l;
        while (i <= m && j <= r) {
            if (arr[i] < arr[j]) {
                buf[k++] = arr[i++];
            } else {
                buf[k++] = arr[j++];
            }
        }

        while (i <= m) {
            buf[k++] = arr[i++];
        }
        while (j <= r) {
            buf[k++] = arr[j++];
        }
        for (i = l; i <= r; i++) {
            arr[i] = buf[i];
        }
    }

}
