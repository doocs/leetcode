class Solution {
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        int n = s.length();
        int[][] cnt = new int[n + 1][26];
        for (int i = 1; i <= n; ++i) {
            int j = s.charAt(i - 1) - 'a';
            for (int k = 0; k < 26; ++k) {
                cnt[i][k] = cnt[i - 1][k];
            }
            cnt[i][j]++;
        }
        List<Boolean> ans = new ArrayList<>();
        for (var q : queries) {
            int left = q[0], right = q[1], k = q[2];
            int x = 0;
            for (int j = 0; j < 26; ++j) {
                x += (cnt[right + 1][j] - cnt[left][j]) & 1;
            }
            ans.add(x / 2 <= k);
        }
        return ans;
    }
}