class Solution {
    private static final int[] T = new int[] {1, 7, 30};
    private int[] costs;
    private int[] days;
    private int[] f;
    private int n;

    public int mincostTickets(int[] days, int[] costs) {
        n = days.length;
        f = new int[n];
        this.costs = costs;
        this.days = days;
        Arrays.fill(f, -1);
        return dfs(0);
    }

    private int dfs(int i) {
        if (i >= n) {
            return 0;
        }
        if (f[i] != -1) {
            return f[i];
        }
        int res = Integer.MAX_VALUE;

        for (int k = 0; k < 3; ++k) {
            int j = lowerBound(days, days[i] + T[k]);
            res = Math.min(res, costs[k] + dfs(j));
        }
        f[i] = res;
        return res;
    }

    private int lowerBound(int[] days, int x) {
        int left = 0, right = days.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (days[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}