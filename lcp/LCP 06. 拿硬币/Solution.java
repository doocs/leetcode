class Solution {
    public int minCount(int[] coins) {
        int ans = 0;
        for (int x : coins) {
            ans += (x + 1) >> 1;
        }
        return ans;
    }
}