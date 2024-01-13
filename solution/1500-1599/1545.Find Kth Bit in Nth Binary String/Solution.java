class Solution {
    public char findKthBit(int n, int k) {
        return (char) ('0' + dfs(n, k));
    }

    private int dfs(int n, int k) {
        if (k == 1) {
            return 0;
        }
        if ((k & (k - 1)) == 0) {
            return 1;
        }
        int m = 1 << n;
        if (k * 2 < m - 1) {
            return dfs(n - 1, k);
        }
        return dfs(n - 1, m - k) ^ 1;
    }
}