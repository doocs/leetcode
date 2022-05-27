class Solution {
    public int maximumRequests(int n, int[][] requests) {
        int ans = 0;
        for (int mask = 1; mask < 1 << requests.length; ++mask) {
            int cnt = Integer.bitCount(mask);
            if (ans < cnt && check(mask, requests)) {
                ans = cnt;
            }
        }
        return ans;
    }

    private boolean check(int x, int[][] requests) {
        int[] d = new int[21];
        for (int i = 0; i < requests.length; ++i) {
            if (((x >> i) & 1) == 1) {
                int f = requests[i][0];
                int t = requests[i][1];
                --d[f];
                ++d[t];
            }
        }
        for (int v : d) {
            if (v != 0) {
                return false;
            }
        }
        return true;
    }
}