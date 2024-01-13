class Solution {
    private int m;
    private int[][] rides;
    private Long[] f;

    public long maxTaxiEarnings(int n, int[][] rides) {
        Arrays.sort(rides, (a, b) -> a[0] - b[0]);
        m = rides.length;
        f = new Long[m];
        this.rides = rides;
        return dfs(0);
    }

    private long dfs(int i) {
        if (i >= m) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        int[] r = rides[i];
        int st = r[0], ed = r[1], tip = r[2];
        int j = search(ed, i + 1);
        return f[i] = Math.max(dfs(i + 1), dfs(j) + ed - st + tip);
    }

    private int search(int x, int l) {
        int r = m;
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