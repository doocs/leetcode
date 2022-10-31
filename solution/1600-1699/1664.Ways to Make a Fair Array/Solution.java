class Solution {
    public int waysToMakeFair(int[] nums) {
        int n = nums.length;
        int x = 0, y = 0;
        for (int i = 0; i < n; ++i) {
            if (i % 2 == 1) {
                x += nums[i];
            } else {
                y += nums[i];
            }
        }
        int ans = 0;
        int a = 0, b = 0;
        for (int i = 0; i < n; ++i) {
            int v = nums[i];
            if (i % 2 == 1 && x - v - a + b == y - b + a) {
                ++ans;
            } else if (i % 2 == 0 && y - v - b + a == x - a + b) {
                ++ans;
            }
            if (i % 2 == 1) {
                a += v;
            } else {
                b += v;
            }
        }
        return ans;
    }
}