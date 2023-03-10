class Solution {
    public String modifyString(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        for (int i = 0; i < n; ++i) {
            if (cs[i] == '?') {
                for (char c = 'a'; c <= 'c'; ++c) {
                    if ((i > 0 && cs[i - 1] == c) || (i + 1 < n && cs[i + 1] == c)) {
                        continue;
                    }
                    cs[i] = c;
                    break;
                }
            }
        }
        return String.valueOf(cs);
    }
}