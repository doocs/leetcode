class Solution {
    public int[] reverseSubarrays(int[] nums, int k) {
        int n = nums.length;
        int m = n / k;
        for (int i = 0; i < n; i += m) {
            int l = i, r = i + m - 1;
            while (l < r) {
                int t = nums[l];
                nums[l++] = nums[r];
                nums[r--] = t;
            }
        }
        return nums;
    }
}
