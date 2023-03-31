class Solution {
    private String s;

    public boolean splitString(String s) {
        this.s = s;
        return dfs(0, -1, 0);
    }

    private boolean dfs(int i, long x, int k) {
        if (i == s.length()) {
            return k > 1;
        }
        long y = 0;
        for (int j = i; j < s.length(); ++j) {
            y = y * 10 + (s.charAt(j) - '0');
            if ((x == -1 || x - y == 1) && dfs(j + 1, y, k + 1)) {
                return true;
            }
        }
        return false;
    }
}