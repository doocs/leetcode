class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        int n = intervals.length;
        int[] d = new int[n + 1];
        d[1] = intervals[0][1];
        int size = 1;
        for (int i = 1; i < n; ++i) {
            int s = intervals[i][0], e = intervals[i][1];
            if (s >= d[size]) {
                d[++size] = e;
            } else {
                int left = 1, right = size;
                while (left < right) {
                    int mid = (left + right) >> 1;
                    if (d[mid] >= s) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                d[left] = Math.min(d[left], e);
            }
        }
        return n - size;
    }
}