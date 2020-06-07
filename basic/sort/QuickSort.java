import java.util.Arrays;

public class QuickSort {

    private static void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private static void quickSort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }
        int[] p = partition(nums, low, high);
        quickSort(nums, low, p[0] - 1);
        quickSort(nums, p[0] + 1, high);
    }

    private static int[] partition(int[] nums, int low, int high) {
        int less = low - 1, more = high;

        while (low < more) {
            if (nums[low] < nums[high]) {
                swap(nums, ++less, low++);
            } else if (nums[low] > nums[high]) {
                swap(nums, --more, low);
            } else {
                ++low;
            }
        }
        swap(nums, more, high);
        return new int[] {less + 1, more};
    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 7, 4, 5, 3};
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}