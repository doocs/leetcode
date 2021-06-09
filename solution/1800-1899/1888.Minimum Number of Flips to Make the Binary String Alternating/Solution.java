class Solution {
    public int minFlips(String s) {
        int n = s.length();
        String target = "01";
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            cnt += (s.charAt(i) == target.charAt(i & 1) ? 0 : 1);
        }
        int res = Math.min(cnt, n - cnt);
        for (int i = 0; i < n; ++i) {
            cnt -= (s.charAt(i) == target.charAt(i & 1) ? 0 : 1);
            cnt += (s.charAt(i) == target.charAt((i + n) & 1) ? 0 : 1);
            res = Math.min(res, Math.min(cnt, n - cnt));
        }
        return res;
    }
}