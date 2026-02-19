class Solution {
    public int countBinarySubstrings(String s) {
        int n = s.length();
        int ans = 0;
        int i = 0;
        int pre = 0;
        while (i < n) {
            int j = i + 1;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                j++;
            }
            int cur = j - i;
            ans += Math.min(pre, cur);
            pre = cur;
            i = j;
        }
        return ans;
    }
}
