class Solution {
    public long maxTaxiEarnings(int n, int[][] rides) {
        Arrays.sort(rides, (a, b) -> a[0] - b[0]);
        int m = rides.length;
        long[] f = new long[m + 1];
        for (int i = m - 1; i >= 0; --i) {
            int st = rides[i][0], ed = rides[i][1], tip = rides[i][2];
            int j = search(rides, ed, i + 1);
            f[i] = Math.max(f[i + 1], f[j] + ed - st + tip);
        }
        return f[0];
    }

    private int search(int[][] rides, int x, int l) {
        int r = rides.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (rides[mid][0] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
