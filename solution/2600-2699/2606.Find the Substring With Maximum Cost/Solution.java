class Solution {
    public int maximumCostSubstring(String s, String chars, int[] vals) {
        int[] d = new int[26];
        for (int i = 0; i < d.length; ++i) {
            d[i] = i + 1;
        }
        int m = chars.length();
        for (int i = 0; i < m; ++i) {
            d[chars.charAt(i) - 'a'] = vals[i];
        }
        int ans = 0, tot = 0, mi = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int v = d[s.charAt(i) - 'a'];
            tot += v;
            ans = Math.max(ans, tot - mi);
            mi = Math.min(mi, tot);
        }
        return ans;
    }
}