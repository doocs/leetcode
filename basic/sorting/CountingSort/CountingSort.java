import java.util.Arrays;

public class CountingSort {
    public static void countingSort(int[] nums, int min, int max) {
        int n = nums.length;
        int k = max - min + 1;
        int[] c = new int[k];
        for (int v : nums) {
            c[v - min]++;
        }

        for (int i = 1; i < k; i++) {
            c[i] += c[i - 1];
        }

        int[] r = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int v = nums[i];
            int a = c[v - min];
            r[a - 1] = v;
            c[v - min]--;
        }
        System.arraycopy(r, 0, nums, 0, n);
    }

    public static void main(String[] args) {

        // test case 1
        int[] nums = {1, 2, 7, 9, 5, 5, 8};
        countingSort(nums, 1, 9);
        System.out.println(Arrays.toString(nums));

        // test case 2
        int[] nums2 = {2, 7, 9, 5, 5, 8};
        countingSort(nums2, 2, 9);
        System.out.println(Arrays.toString(nums2));
    }
}
