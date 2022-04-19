class Solution {
    public int[] shortestToChar(String s, char c) {
        int n = s.length();
        int[] ans = new int[n];
        for (int i = 0, j = Integer.MAX_VALUE; i < n; ++i) {
            if (s.charAt(i) == c) {
                j = i;
            }
            ans[i] = Math.abs(i - j);
        }
        for (int i = n - 1, j = Integer.MAX_VALUE; i >= 0; --i) {
            if (s.charAt(i) == c) {
                j = i;
            }
            ans[i] = Math.min(ans[i], Math.abs(i - j));
        }
        return ans;
    }
}