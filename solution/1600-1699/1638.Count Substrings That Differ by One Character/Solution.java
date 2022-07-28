class Solution {
    public int countSubstrings(String s, String t) {
        int m = s.length(), n = t.length();
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (s.charAt(i) != t.charAt(j)) {
                    int l = 1, r = 1;
                    while (i - l >= 0 && j - l >= 0 && s.charAt(i - l) == t.charAt(j - l)) {
                        ++l;
                    }
                    while (i + r < m && j + r < n && s.charAt(i + r) == t.charAt(j + r)) {
                        ++r;
                    }
                    ans += l * r;
                }
            }
        }
        return ans;
    }
}