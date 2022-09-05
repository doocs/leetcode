import java.util.Arrays;

public class ShellSort {

    private static int[] shellSort(int[] arr) {
        int n = arr.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int key = arr[i];
                int j = i;
                while (j >= gap && arr[j - gap] > key) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = key;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(shellSort(new int[] {1, 2, 7, 9, 5, 8})));
    }
}