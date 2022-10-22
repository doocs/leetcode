class Solution {
    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> a[1] - b[1]);
        int n = events.length;
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i < n; ++i) {
            int s = events[i][0], v = events[i][2];
            int h = search(events, s, i);
            for (int j = 1; j <= k; ++j) {
                dp[i + 1][j] = Math.max(dp[i][j], dp[h][j - 1] + v);
            }
        }
        return dp[n][k];
    }

    private int search(int[][] events, int x, int n) {
        int left = 0, right = n;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (events[mid][1] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}