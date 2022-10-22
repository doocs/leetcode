class Solution {
    private int[][] rides;
    private long[] f;
    private int m;

    public long maxTaxiEarnings(int n, int[][] rides) {
        m = rides.length;
        f = new long[m];
        Arrays.sort(rides, (a, b) -> a[0] - b[0]);
        this.rides = rides;
        return dfs(0);
    }

    private long dfs(int i) {
        if (i >= m) {
            return 0;
        }
        if (f[i] != 0) {
            return f[i];
        }
        int s = rides[i][0], e = rides[i][1], t = rides[i][2];
        int j = search(rides, e, i + 1);
        long ans = Math.max(dfs(i + 1), dfs(j) + e - s + t);
        f[i] = ans;
        return ans;
    }

    private int search(int[][] rides, int x, int i) {
        int left = i, right = m;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (rides[mid][0] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}