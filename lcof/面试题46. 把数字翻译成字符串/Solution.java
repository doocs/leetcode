class Solution {
    private int n;
    private char[] s;
    private Integer[] f;

    public int translateNum(int num) {
        s = String.valueOf(num).toCharArray();
        n = s.length;
        f = new Integer[n];
        return dfs(0);
    }

    private int dfs(int i) {
        if (i >= n - 1) {
            return 1;
        }
        if (f[i] != null) {
            return f[i];
        }
        int ans = dfs(i + 1);
        if (s[i] == '1' || (s[i] == '2' && s[i + 1] < '6')) {
            ans += dfs(i + 2);
        }
        return f[i] = ans;
    }
}