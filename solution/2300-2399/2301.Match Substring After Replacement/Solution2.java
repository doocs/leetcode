class Solution {
    public boolean matchReplacement(String s, String sub, char[][] mappings) {
        boolean[][] d = new boolean[128][128];
        for (var e : mappings) {
            d[e[0]][e[1]] = true;
        }
        int m = s.length(), n = sub.length();
        for (int i = 0; i < m - n + 1; ++i) {
            boolean ok = true;
            for (int j = 0; j < n && ok; ++j) {
                char a = s.charAt(i + j), b = sub.charAt(j);
                if (a != b && !d[b][a]) {
                    ok = false;
                }
            }
            if (ok) {
                return true;
            }
        }
        return false;
    }
}