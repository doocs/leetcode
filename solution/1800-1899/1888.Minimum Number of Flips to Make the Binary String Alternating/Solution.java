class Solution {
    public int minFlips(String s) {
        int n = s.length();
        String target = "01";
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) != target.charAt(i & 1)) {
                ++cnt;
            }
        }
        int ans = Math.min(cnt, n - cnt);
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) != target.charAt(i & 1)) {
                --cnt;
            }
            if (s.charAt(i) != target.charAt((i + n) & 1)) {
                ++cnt;
            }
            ans = Math.min(ans, Math.min(cnt, n - cnt));
        }
        return ans;
    }
}