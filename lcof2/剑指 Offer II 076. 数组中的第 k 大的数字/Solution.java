class Solution {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        return quickSort(nums, 0, n - 1, n - k);
    }

    private int quickSort(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }
        int i = left - 1, j = right + 1;
        int x = nums[(left + right) >>> 1];
        while (i < j) {
            while (nums[++i] < x)
                ;
            while (nums[--j] > x)
                ;
            if (i < j) {
                int t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
            }
        }
        if (j < k) {
            return quickSort(nums, j + 1, right, k);
        }
        return quickSort(nums, left, j, k);
    }
}