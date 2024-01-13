class Solution {
    private int[][] jobs;
    private int[] f;
    private int n;

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        n = profit.length;
        jobs = new int[n][3];
        for (int i = 0; i < n; ++i) {
            jobs[i] = new int[] {startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        f = new int[n];
        return dfs(0);
    }

    private int dfs(int i) {
        if (i >= n) {
            return 0;
        }
        if (f[i] != 0) {
            return f[i];
        }
        int e = jobs[i][1], p = jobs[i][2];
        int j = search(jobs, e, i + 1);
        int ans = Math.max(dfs(i + 1), p + dfs(j));
        f[i] = ans;
        return ans;
    }

    private int search(int[][] jobs, int x, int i) {
        int left = i, right = n;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (jobs[mid][0] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}