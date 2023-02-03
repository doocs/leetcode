class Solution {
    private int[] a = new int[12];
    private Integer[][] f = new Integer[12][12];

    public int countDigitOne(int n) {
        int i = -1;
        for (; n > 0; n /= 10) {
            a[++i] = n % 10;
        }
        return dfs(i, 0, true);
    }

    private int dfs(int pos, int cnt, boolean limit) {
        if (pos < 0) {
            return cnt;
        }
        if (!limit && f[pos][cnt] != null) {
            return f[pos][cnt];
        }
        int up = limit ? a[pos] : 9;
        int ans = 0;
        for (int i = 0; i <= up; ++i) {
            ans += dfs(pos - 1, cnt + (i == 1 ? 1 : 0), limit && i == up);
        }
        return f[pos][cnt] = ans;
    }
}