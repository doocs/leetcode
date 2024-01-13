class Solution {
    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        int[] d = new int[n + 1];
        int ans = 0, s = 0;
        for (int i = 0; i < n; ++i) {
            s += d[i];
            if (nums[i] % 2 == s % 2) {
                if (i + k > n) {
                    return -1;
                }
                ++d[i];
                --d[i + k];
                ++s;
                ++ans;
            }
        }
        return ans;
    }
}