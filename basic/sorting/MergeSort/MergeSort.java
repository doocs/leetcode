import java.util.Arrays;

public class MergeSort {

    private static void merge(int[] nums, int low, int mid, int high, int[] temp) {
        int i = low, j = mid + 1, k = low;
        while (k <= high) {
            if (i > mid) {
                temp[k++] = nums[j++];
            } else if (j > high) {
                temp[k++] = nums[i++];
            } else if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }

        System.arraycopy(tmp, low, nums, low, high - low + 1);
    }

    private static void mergeSort(int[] nums, int low, int high, int[] temp) {
        if (low >= high) {
            return;
        }
        int mid = low + ((high - low) >> 1);
        mergeSort(nums, low, mid, temp);
        mergeSort(nums, mid + 1, high, temp);
        merge(nums, low, mid, high, temp);
    }

    private static void mergeSort(int[] nums) {
        int[] temp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1, temp);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 7, 4, 5, 3};
        mergeSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}