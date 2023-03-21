class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] s = new char[n];
        int i = 0;
        for (char c = 'a'; c <= 'z'; ++c) {
            while (i < n && s[i] != '\0') {
                ++i;
            }
            if (i == n) {
                break;
            }
            for (int j = i; j < n; ++j) {
                if (lcp[i][j] > 0) {
                    s[j] = c;
                }
            }
        }
        for (i = 0; i < n; ++i) {
            if (s[i] == '\0') {
                return "";
            }
        }
        for (i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (s[i] == s[j]) {
                    if (i == n - 1 || j == n - 1) {
                        if (lcp[i][j] != 1) {
                            return "";
                        }
                    } else if (lcp[i][j] != lcp[i + 1][j + 1] + 1) {
                        return "";
                    }
                } else if (lcp[i][j] > 0) {
                    return "";
                }
            }
        }
        return String.valueOf(s);
    }
}