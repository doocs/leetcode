class Solution {
    public long[] countKConstraintSubstrings(String s, int k, int[][] queries) {
        int[] cnt = new int[2];
        int n = s.length();
        int[] d = new int[n];
        Arrays.fill(d, n);
        long[] pre = new long[n + 1];
        for (int i = 0, j = 0; j < n; ++j) {
            cnt[s.charAt(j) - '0']++;
            while (cnt[0] > k && cnt[1] > k) {
                d[i] = j;
                cnt[s.charAt(i++) - '0']--;
            }
            pre[j + 1] = pre[j] + j - i + 1;
        }
        int m = queries.length;
        long[] ans = new long[m];
        for (int i = 0; i < m; ++i) {
            int l = queries[i][0], r = queries[i][1];
            int p = Math.min(r + 1, d[l]);
            long a = (1L + p - l) * (p - l) / 2;
            long b = pre[r + 1] - pre[p];
            ans[i] = a + b;
        }
        return ans;
    }
}
