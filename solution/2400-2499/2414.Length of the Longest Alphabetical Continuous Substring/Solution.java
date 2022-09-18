class Solution {
    public int longestContinuousSubstring(String s) {
        int ans = 0;
        int i = 0, j = 1;
        for (; j < s.length(); ++j) {
            ans = Math.max(ans, j - i);
            if (s.charAt(j) - s.charAt(j - 1) != 1) {
                i = j;
            }
        }
        ans = Math.max(ans, j - i);
        return ans;
    }
}