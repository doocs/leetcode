class Solution {
    public int maxDifference(String S, int k) {
        char[] s = S.toCharArray();
        int n = s.length;
        final int inf = Integer.MAX_VALUE / 2;
        int ans = -inf;
        for (int a = 0; a < 5; ++a) {
            for (int b = 0; b < 5; ++b) {
                if (a == b) {
                    continue;
                }
                int curA = 0, curB = 0;
                int preA = 0, preB = 0;
                int[][] t = {{inf, inf}, {inf, inf}};
                for (int l = -1, r = 0; r < n; ++r) {
                    curA += s[r] == '0' + a ? 1 : 0;
                    curB += s[r] == '0' + b ? 1 : 0;
                    while (r - l >= k && curB - preB >= 2) {
                        t[preA & 1][preB & 1] = Math.min(t[preA & 1][preB & 1], preA - preB);
                        ++l;
                        preA += s[l] == '0' + a ? 1 : 0;
                        preB += s[l] == '0' + b ? 1 : 0;
                    }
                    ans = Math.max(ans, curA - curB - t[curA & 1 ^ 1][curB & 1]);
                }
            }
        }
        return ans;
    }
}