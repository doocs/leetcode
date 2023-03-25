class Solution {
    public int minTime(int[] time, int m) {
        int left = 0, right = 0;
        for (int x : time) {
            right += x;
        }
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(mid, time, m)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int t, int[] time, int m) {
        int s = 0, mx = 0;
        int d = 1;
        for (int x : time) {
            s += x;
            mx = Math.max(mx, x);
            if (s - mx > t) {
                s = x;
                mx = x;
                ++d;
            }
        }
        return d <= m;
    }
}