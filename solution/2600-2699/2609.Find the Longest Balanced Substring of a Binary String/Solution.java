class Solution {
    public int findTheLongestBalancedSubstring(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (check(s, i, j)) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }

    private boolean check(String s, int i, int j) {
        int cnt = 0;
        for (int k = i; k <= j; ++k) {
            if (s.charAt(k) == '1') {
                ++cnt;
            } else if (cnt > 0) {
                return false;
            }
        }
        return cnt * 2 == j - i + 1;
    }
}