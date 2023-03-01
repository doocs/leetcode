class Solution {
    public int maxProduct(String s) {
        int n = s.length();
        boolean[] p = new boolean[1 << n];
        Arrays.fill(p, true);
        for (int k = 1; k < 1 << n; ++k) {
            for (int i = 0, j = n - 1; i < n; ++i, --j) {
                while (i < j && (k >> i & 1) == 0) {
                    ++i;
                }
                while (i < j && (k >> j & 1) == 0) {
                    --j;
                }
                if (i < j && s.charAt(i) != s.charAt(j)) {
                    p[k] = false;
                    break;
                }
            }
        }
        int ans = 0;
        for (int i = 1; i < 1 << n; ++i) {
            if (p[i]) {
                int a = Integer.bitCount(i);
                int mx = ((1 << n) - 1) ^ i;
                for (int j = mx; j > 0; j = (j - 1) & mx) {
                    if (p[j]) {
                        int b = Integer.bitCount(j);
                        ans = Math.max(ans, a * b);
                    }
                }
            }
        }
        return ans;
    }
}