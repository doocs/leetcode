class Solution {
    public long numberOfWays(String s) {
        int n = s.length();
        int cnt0 = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                ++cnt0;
            }
        }
        int cnt1 = n - cnt0;
        long ans = 0;
        int c0 = 0, c1 = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                ans += c1 * (cnt1 - c1);
                ++c0;
            } else {
                ans += c0 * (cnt0 - c0);
                ++c1;
            }
        }
        return ans;
    }
}