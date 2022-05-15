class Solution {
    public int largestCombination(int[] candidates) {
        int ans = 0;
        for (int i = 0; i < 32; ++i) {
            int t = 0;
            for (int x : candidates) {
                t += (x >> i) & 1;
            }
            ans = Math.max(ans, t);
        }
        return ans;
    }
}