class Solution {
    private Integer[] f;
    private int[] s;
    private int k;
    private int n;

    public int minimumCost(String sentence, int k) {
        this.k = k;
        String[] words = sentence.split(" ");
        n = words.length;
        f = new Integer[n];
        s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + words[i].length();
        }
        return dfs(0);
    }

    private int dfs(int i) {
        if (s[n] - s[i] + n - i - 1 <= k) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        int ans = Integer.MAX_VALUE;
        for (int j = i + 1; j < n && s[j] - s[i] + j - i - 1 <= k; ++j) {
            int m = s[j] - s[i] + j - i - 1;
            ans = Math.min(ans, dfs(j) + (k - m) * (k - m));
        }
        return f[i] = ans;
    }
}
