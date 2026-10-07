class Solution {
    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        int n = events.length;
        int[][] f = new int[n + 1][k + 1];
        for (int i = n - 1; i >= 0; --i) {
            int ed = events[i][1], val = events[i][2];
            int j = search(events, ed, i + 1);
            for (int c = 0; c <= k; ++c) {
                f[i][c] = f[i + 1][c];
                if (c > 0) {
                    f[i][c] = Math.max(f[i][c], f[j][c - 1] + val);
                }
            }
        }
        return f[0][k];
    }

    private int search(int[][] events, int x, int lo) {
        int l = lo, r = events.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (events[mid][0] > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
