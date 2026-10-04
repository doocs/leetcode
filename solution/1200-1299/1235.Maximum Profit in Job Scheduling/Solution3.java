class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = profit.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; ++i) {
            jobs[i] = new int[] {startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        int[] f = new int[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            int e = jobs[i][1], p = jobs[i][2];
            int j = search(jobs, e, i + 1);
            f[i] = Math.max(f[i + 1], p + f[j]);
        }
        return f[0];
    }

    private int search(int[][] jobs, int x, int i) {
        int left = i, right = jobs.length;
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
