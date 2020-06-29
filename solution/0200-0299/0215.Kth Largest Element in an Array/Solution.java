class Solution {
    public int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums, 0, nums.length - 1, nums.length - k);
    }
    
    public int findKthLargest(int[] nums, int l, int r, int k) {
        int i = l, j = r;
        int temp = nums[i];
        while (i < j) {
            while (i < j && nums[j] >= temp) {
                j--;
            }
            if (i < j) {
                nums[i++] = nums[j];
            }
            while (i < j && nums[i] <= temp) {
                i++;
            }
            if (i < j) {
                nums[j--] = nums[i];
            }
        }
        nums[i] = temp;
        if (i == k) {
            return nums[i];
        } else if (i < k) {
            return findKthLargest(nums, i + 1, r, k);
        } else {
            return findKthLargest(nums, l, i - 1, k);
        }
    }
}