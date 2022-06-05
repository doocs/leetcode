class Solution {
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        int d = 0, ans = 1;
        for (int i = 1; i < nums.length; ++i) {
            int a = nums[i - 1], b = nums[i];
            int t = b - a;
            if (d + t <= k) {
                d += t;
            } else {
                d = 0;
                ++ans;
            }
        }
        return ans;
    }
}