class Solution {
    public int minMoves(int[][] rooks) {
        Arrays.sort(rooks, (a, b) -> a[0] - b[0]);
        int ans = 0;
        int n = rooks.length;
        for (int i = 0; i < n; ++i) {
            ans += Math.abs(rooks[i][0] - i);
        }
        Arrays.sort(rooks, (a, b) -> a[1] - b[1]);
        for (int j = 0; j < n; ++j) {
            ans += Math.abs(rooks[j][1] - j);
        }
        return ans;
    }
}