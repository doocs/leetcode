class Solution {
    private int m;
    private int n;
    private int[][] requests;

    public int maximumRequests(int n, int[][] requests) {
        m = requests.length;
        this.n = n;
        this.requests = requests;
        int ans = 0;
        for (int mask = 0; mask < 1 << m; ++mask) {
            int cnt = Integer.bitCount(mask);
            if (ans < cnt && check(mask)) {
                ans = cnt;
            }
        }
        return ans;
    }

    private boolean check(int mask) {
        int[] cnt = new int[n];
        for (int i = 0; i < m; ++i) {
            if ((mask >> i & 1) == 1) {
                int f = requests[i][0], t = requests[i][1];
                --cnt[f];
                ++cnt[t];
            }
        }
        for (int v : cnt) {
            if (v != 0) {
                return false;
            }
        }
        return true;
    }
}