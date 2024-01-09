class Solution {
    public int minOperations(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < 20; ++i) {
            int v = 0;
            for (int x : nums) {
                v ^= (x >> i & 1);
            }
            ans += k >> i & 1 ^ v;
        }
        return ans;
    }
}