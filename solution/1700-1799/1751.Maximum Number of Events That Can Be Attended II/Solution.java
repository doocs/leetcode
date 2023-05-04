class Solution {
    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> a[1] - b[1]);
        int n = events.length;
        int[][] f = new int[n + 1][k + 1];
        for (int i = 1; i <= n; ++i) {
            int st = events[i - 1][0], val = events[i - 1][2];
            int p = search(events, st, i - 1);
            for (int j = 1; j <= k; ++j) {
                f[i][j] = Math.max(f[i - 1][j], f[p][j - 1] + val);
            }
        }
        return f[n][k];
    }

    private int search(int[][] events, int x, int hi) {
        int l = 0, r = hi;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (events[mid][1] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}