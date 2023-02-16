class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();
        int sum = 0;
        int ans = 0;
        for (int i = 0, j = 0; i < n; ++i) {
            sum += Math.abs(s.charAt(i) - t.charAt(i));
            while (sum > maxCost) {
                sum -= Math.abs(s.charAt(j) - t.charAt(j));
                ++j;
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}