class Solution {
    public long kMirror(int k, int n) {
        long ans = 0;
        for (int l = 1;; ++l) {
            int x = (int) Math.pow(10, (l - 1) / 2);
            int y = (int) Math.pow(10, (l + 1) / 2);
            for (int i = x; i < y; i++) {
                long v = i;
                for (int j = l % 2 == 0 ? i : i / 10; j > 0; j /= 10) {
                    v = v * 10 + j % 10;
                }
                String ss = Long.toString(v, k);
                if (check(ss.toCharArray())) {
                    ans += v;
                    if (--n == 0) {
                        return ans;
                    }
                }
            }
        }
    }

    private boolean check(char[] c) {
        for (int i = 0, j = c.length - 1; i < j; i++, j--) {
            if (c[i] != c[j]) {
                return false;
            }
        }
        return true;
    }
}