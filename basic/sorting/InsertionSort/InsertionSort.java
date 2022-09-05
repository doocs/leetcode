import java.util.Arrays;

public class InsertionSort {

    private static void insertionSort(int[] nums) {
        for (int i = 1, j, n = nums.length; i < n; ++i) {
            int num = nums[i];
            for (j = i - 1; j >= 0 && nums[j] > num; --j) {
                nums[j + 1] = nums[j];
            }
            nums[j + 1] = num;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 7, 9, 5, 8};
        insertionSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
