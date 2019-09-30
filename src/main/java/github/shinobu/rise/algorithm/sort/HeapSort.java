package github.shinobu.rise.algorithm.sort;

/**
 * @author Shinobu
 * @since 2019/8/12
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = new int[]{8, 8, 9, 7, 3, 1, 4, 9};
        max(arr, arr.length / 2 - 1, arr.length);
        System.out.println(1);
    }

    public static void max(int[] arr, int i, int size) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int max = i;
        if (left == arr.length - 1) {
            if (left < size && arr[left] > arr[max]) {
                max = left;
            }
        } else {
            if (left < size && arr[left] > arr[max]) {
                max = left;
            }
            if (right < size && arr[right] > arr[max]) {
                max = right;
            }
        }

        if (max != i) {
            int temp = arr[i];
            arr[i] = arr[max];
            arr[max] = temp;
            max(arr, max, size);
        }
    }

}
