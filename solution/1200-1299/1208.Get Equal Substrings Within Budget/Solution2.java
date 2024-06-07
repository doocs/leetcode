class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();
        int ans = 0, cost = 0;
        for (int l = 0, r = 0; r < n; ++r) {
            cost += Math.abs(s.charAt(r) - t.charAt(r));
            while (cost > maxCost) {
                cost -= Math.abs(s.charAt(l) - t.charAt(l));
                ++l;
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
