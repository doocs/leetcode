class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        if (m * k > bloomDay.length) {
            return -1;
        }
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int bd : bloomDay) {
            min = Math.min(min, bd);
            max = Math.max(max, bd);
        }
        int left = min, right = max;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (check(bloomDay, m, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[] bloomDay, int m, int k, int day) {
        int cnt = 0, cur = 0;
        for (int bd : bloomDay) {
            cur = bd <= day ? cur + 1 : 0;
            if (cur == k) {
                cnt++;
                cur = 0;
            }
        }
        return cnt >= m;
    }
}
