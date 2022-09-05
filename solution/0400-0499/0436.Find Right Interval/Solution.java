class Solution {
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        List<int[]> starts = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            starts.add(new int[] {intervals[i][0], i});
        }
        starts.sort(Comparator.comparingInt(a -> a[0]));
        int[] res = new int[n];
        int i = 0;
        for (int[] interval : intervals) {
            int left = 0, right = n - 1;
            int end = interval[1];
            while (left < right) {
                int mid = (left + right) >> 1;
                if (starts.get(mid)[0] >= end) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            res[i++] = starts.get(left)[0] < end ? -1 : starts.get(left)[1];
        }
        return res;
    }
}