class Solution {
    public int minFlipsMonoIncr(String s) {
        int n = s.length();
        int[] presum = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            presum[i + 1] = presum[i] + (s.charAt(i) - '0');
        }
        int ans = presum[n];
        for (int i = 0; i < n; ++i) {
            ans = Math.min(ans, presum[i] + n - i - (presum[n] - presum[i]));
        }
        return ans;
    }
}