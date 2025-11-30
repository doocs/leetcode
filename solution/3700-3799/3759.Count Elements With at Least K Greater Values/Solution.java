class Solution {
    public int countElements(int[] nums, int k) {
        int n = nums.length;
        if (k == 0) {
            return n;
        }
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < n - k; ++i) {
            if (nums[n - k] > nums[i]) {
                ++ans;
            }
        }
        return ans;
    }
}
