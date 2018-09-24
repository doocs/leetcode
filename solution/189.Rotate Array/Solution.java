class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        if (n < 2 || k == 0) {
            return;
        }
        
        rotate(nums, 0, n - 1);
        rotate(nums, 0, k - 1);
        rotate(nums, k, n - 1);
    }
    
    private void rotate(int[] nums, int start, int end) {
        while (start < end) {
            int t = nums[start];
            nums[start] = nums[end];
            nums[end] = t;
            ++start;
            --end;
        }
    }
}