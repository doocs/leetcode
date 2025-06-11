class Solution {
    public int minOperations(String word1, String word2) {
        int n = word1.length();
        int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                int a = calc(word1, word2, j, i - 1, false);
                int b = 1 + calc(word1, word2, j, i - 1, true);
                int t = Math.min(a, b);
                f[i] = Math.min(f[i], f[j] + t);
            }
        }
        return f[n];
    }

    private int calc(String word1, String word2, int l, int r, boolean rev) {
        int[][] cnt = new int[26][26];
        int res = 0;
        for (int i = l; i <= r; i++) {
            int j = rev ? r - (i - l) : i;
            int a = word1.charAt(j) - 'a';
            int b = word2.charAt(i) - 'a';
            if (a != b) {
                if (cnt[b][a] > 0) {
                    cnt[b][a]--;
                } else {
                    cnt[a][b]++;
                    res++;
                }
            }
        }
        return res;
    }
}
