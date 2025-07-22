class Solution {
    private char[] s;
    private int numOps;

    public int minLength(String s, int numOps) {
        this.numOps = numOps;
        this.s = s.toCharArray();
        int l = 1, r = s.length();
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean check(int m) {
        int cnt = 0;
        if (m == 1) {
            char[] t = {'0', '1'};
            for (int i = 0; i < s.length; ++i) {
                if (s[i] == t[i & 1]) {
                    ++cnt;
                }
            }
            cnt = Math.min(cnt, s.length - cnt);
        } else {
            int k = 0;
            for (int i = 0; i < s.length; ++i) {
                ++k;
                if (i == s.length - 1 || s[i] != s[i + 1]) {
                    cnt += k / (m + 1);
                    k = 0;
                }
            }
        }
        return cnt <= numOps;
    }
}
