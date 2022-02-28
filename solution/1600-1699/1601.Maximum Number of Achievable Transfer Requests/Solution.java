class Solution {
    private int m;
    private int n;
    private int[][] requests;

    public int maximumRequests(int n, int[][] requests) {
        int ans = 0;
        m = requests.length;
        this.n = n;
        this.requests = requests;
        for (int mask = 0; mask < (1 << m); ++mask) {
            int cnt = Integer.bitCount(mask);
            if (cnt <= ans) {
                continue;
            }
            if (check(mask)) {
                ans = cnt;
            }
        }
        return ans;
    }

    private boolean check(int x) {
        int[] delta = new int[n];
        for (int i = 0; i < m; ++i) {
            if (((x >> i) & 1) == 1) {
                int f = requests[i][0];
                int t = requests[i][1];
                --delta[f];
                ++delta[t];
            }
        }
        for (int i = 0; i < n; ++i) {
            if (delta[i] != 0) {
                return false;
            }
        }
        return true;
    }
}