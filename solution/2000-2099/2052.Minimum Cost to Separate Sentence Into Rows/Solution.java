class Solution {
    private static final int INF = Integer.MAX_VALUE;
    private int[] memo;
    private int[] s;
    private int n;

    public int minimumCost(String sentence, int k) {
        String[] words = sentence.split(" ");
        n = words.length;
        s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + words[i].length();
        }
        memo = new int[n];
        Arrays.fill(memo, INF);
        return dfs(0, k);
    }

    private int dfs(int i, int k) {
        if (memo[i] != INF) {
            return memo[i];
        }
        if (s[n] - s[i] + n - i - 1 <= k) {
            memo[i] = 0;
            return 0;
        }
        int ans = INF;
        for (int j = i + 1; j < n; ++j) {
            int t = s[j] - s[i] + j - i - 1;
            if (t <= k) {
                ans = Math.min(ans, (k - t) * (k - t) + dfs(j, k));
            }
        }
        memo[i] = ans;
        return ans;
    }
}