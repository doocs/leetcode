class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0] == 0 ? b[1] - a[1] : a[0] - b[0]);
        int[] pre = intervals[0];
        int cnt = 1;
        for (int i = 1; i < intervals.length; ++i) {
            if (pre[1] < intervals[i][1]) {
                ++cnt;
                pre = intervals[i];
            }
        }
        return cnt;
    }
}