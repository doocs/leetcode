class Solution {
    public int minOperations(int[] nums) {
        int ans = 0;
        int mx = 0;
        for (int v : nums) {
            mx = Math.max(mx, v);
            ans += Integer.bitCount(v);
        }
        ans += Integer.toBinaryString(mx).length() - 1;
        return ans;
    }
}