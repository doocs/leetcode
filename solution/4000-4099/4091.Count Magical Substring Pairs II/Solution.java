class Solution {
    private long[][] calc(String s) {
        int n = s.length();
        long[][] f = new long[n][27];
        for (int k = 1; k <= 26; k++) {
            int[] cnt = new int[26];
            int l = 0, distinct = 0;
            for (int r = 0; r < n; r++) {
                if (cnt[s.charAt(r) - 'a']++ == 0) {
                    distinct++;
                }
                while (distinct > k) {
                    if (--cnt[s.charAt(l++) - 'a'] == 0) {
                        distinct--;
                    }
                }
                f[r][k] = r - l + 1L;
            }
        }
        return f;
    }

    public long countMagicalPairs(String s) {
        int n = s.length();
        long[][] left = calc(s);
        long[][] right = calc(new StringBuilder(s).reverse().toString());
        long ans = 0;
        for (int j = 0; j < n - 1; j++) {
            int r = n - 2 - j;
            for (int k = 1; k <= 26; k++) {
                ans += (left[j][k] - left[j][k - 1]) * (right[r][k] - right[r][k - 1]);
            }
        }
        return ans;
    }
}