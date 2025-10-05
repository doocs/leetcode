class Solution {
    public int longestSubsequence(int[] nums) {
        int xor = 0, cnt0 = 0;
        int n = nums.length;
        for (int x : nums) {
            xor ^= x;
            cnt0 += x == 0 ? 1 : 0;
        }
        if (xor != 0) {
            return n;
        }
        return cnt0 == n ? 0 : n - 1;
    }
}
