class Solution {
    public long maxEarnings(int[][] meetings) {
        int n = meetings.length;
        Arrays.sort(meetings, (a, b) -> a[1] - b[1]);

        long[] preMax = new long[n + 1];
        Arrays.fill(preMax, Long.MIN_VALUE / 2);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int start = meetings[i][0];
            int end = meetings[i][1];
            int revenue = meetings[i][2];

            long val = revenue;
            if (start >= meetings[0][1]) {
                int j = upperBound(meetings, start, i);
                val += preMax[j] + start;
            }

            ans = Math.max(ans, val);
            preMax[i + 1] = Math.max(preMax[i], val - end);
        }

        return ans;
    }

    private int upperBound(int[][] meetings, int target, int hi) {
        int l = 0, r = hi;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (meetings[m][1] <= target) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }
}