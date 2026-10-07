class Solution {
    public int minimumCost(String sentence, int k) {
        String[] words = sentence.split(" ");
        int n = words.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + words[i].length();
        }
        int[] f = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            if (s[n] - s[i] + n - i - 1 <= k) {
                continue;
            }
            int ans = Integer.MAX_VALUE;
            for (int j = i + 1; j < n && s[j] - s[i] + j - i - 1 <= k; ++j) {
                int m = s[j] - s[i] + j - i - 1;
                ans = Math.min(ans, f[j] + (k - m) * (k - m));
            }
            f[i] = ans;
        }
        return f[0];
    }
}
