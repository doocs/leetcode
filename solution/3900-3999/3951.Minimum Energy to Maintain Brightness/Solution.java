class Solution {
    public long minEnergy(int n, int brightness, int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> merged = new ArrayList<>();
        merged.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] x = intervals[i];
            int[] last = merged.get(merged.size() - 1);
            if (last[1] < x[0]) {
                merged.add(x);
            } else {
                last[1] = Math.max(last[1], x[1]);
            }
        }
        long ans = 0;
        for (int[] interval : merged) {
            int start = interval[0];
            int end = interval[1];
            int m = end - start + 1;
            ans += (brightness + 2L) / 3 * m;
        }
        return ans;
    }
}