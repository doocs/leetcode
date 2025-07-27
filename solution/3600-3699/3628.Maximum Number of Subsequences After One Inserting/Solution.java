class Solution {
    private char[] s;

    public long numOfSubsequences(String S) {
        s = S.toCharArray();
        int l = 0, r = 0;
        for (char c : s) {
            if (c == 'T') {
                ++r;
            }
        }
        long ans = 0, mx = 0;
        for (char c : s) {
            r -= c == 'T' ? 1 : 0;
            if (c == 'C') {
                ans += 1L * l * r;
            }
            l += c == 'L' ? 1 : 0;
            mx = Math.max(mx, 1L * l * r);
        }
        mx = Math.max(mx, Math.max(calc("LC"), calc("CT")));
        ans += mx;
        return ans;
    }

    private long calc(String t) {
        long cnt = 0;
        int a = 0;
        for (char c : s) {
            if (c == t.charAt(1)) {
                cnt += a;
            }
            a += c == t.charAt(0) ? 1 : 0;
        }
        return cnt;
    }
}