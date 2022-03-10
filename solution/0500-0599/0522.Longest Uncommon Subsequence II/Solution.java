class Solution {
    public int findLUSlength(String[] strs) {
        int ans = -1;
        for (int i = 0, j = 0, n = strs.length; i < n; ++i) {
            for (j = 0; j < n; ++j) {
                if (i == j) {
                    continue;
                }
                if (check(strs[j], strs[i])) {
                    break;
                }
            }
            if (j == n) {
                ans = Math.max(ans, strs[i].length());
            }
        }
        return ans;
    }

    private boolean check(String a, String b) {
        int j = 0;
        for (int i = 0; i < a.length() && j < b.length(); ++i) {
            if (a.charAt(i) == b.charAt(j)) {
                ++j;
            }
        }
        return j == b.length();
    }
}