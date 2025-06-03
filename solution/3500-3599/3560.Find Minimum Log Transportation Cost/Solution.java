class Solution {
    public long minCuttingCost(int n, int m, int k) {
        int x = Math.max(n, m);
        return x <= k ? 0 : 1L * k * (x - k);
    }
}