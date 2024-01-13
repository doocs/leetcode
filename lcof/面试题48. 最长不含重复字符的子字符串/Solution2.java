class Solution {
    public int lengthOfLongestSubstring(String s) {
        boolean[] ss = new boolean[128];
        int ans = 0, j = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            while (ss[c]) {
                ss[s.charAt(j++)] = false;
            }
            ans = Math.max(ans, i - j + 1);
            ss[c] = true;
        }
        return ans;
    }
}