class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int left = 0, right = 0;
        for (int w : weights) {
            left = Math.max(left, w);
            right += w;
        }
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(mid, weights, days)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int mx, int[] weights, int days) {
        int ws = 0, cnt = 1;
        for (int w : weights) {
            ws += w;
            if (ws > mx) {
                ws = w;
                ++cnt;
            }
        }
        return cnt <= days;
    }
}