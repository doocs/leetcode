class Solution {
    public int findKthLargest(int[] nums, int k) {
        return find(nums, 0, nums.length - 1, k - 1);
    }
    
    private int find(int[] nums, int left, int right, int k) {
        int i = left;
        int j = right;
        int index = nums[left];
        while (i < j) {
            while (i < j && index >= nums[j]) j--;
            if (i < j) {
                nums[i++] = nums[j];
            }
            while (i < j && index <= nums[i]) i++;
            if (i < j) {
                nums[j--] = nums[i];
            }
        }
        nums[i] = index;
        if (i > k) return find (nums, left, i - 1, k);
        else if (i < k) return find(nums, i + 1, right, k);
        else return nums[i];
    }
}