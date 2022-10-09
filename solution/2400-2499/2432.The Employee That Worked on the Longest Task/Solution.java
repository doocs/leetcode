class Solution {
    public int hardestWorker(int n, int[][] logs) {
        int ans = 0, mx = 0, last = 0;
        for (var e : logs) {
            int uid = e[0], t = e[1];
            int x = t - last;
            if (mx < x) {
                mx = x;
                ans = uid;
            } else if (mx == x && ans > uid) {
                ans = uid;
            }
            last = t;
        }
        return ans;
    }
}