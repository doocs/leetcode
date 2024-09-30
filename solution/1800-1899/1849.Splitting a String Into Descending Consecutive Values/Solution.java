class Solution {
    private char[] s;

    public boolean splitString(String s) {
        this.s = s.toCharArray();
        return dfs(0, -1);
    }

    private boolean dfs(int i, long x) {
        if (i >= s.length) {
            return true;
        }
        long y = 0;
        int r = x < 0 ? s.length - 1 : s.length;
        for (int j = i; j < r; ++j) {
            y = y * 10 + s[j] - '0';
            if ((x < 0 || x - y == 1) && dfs(j + 1, y)) {
                return true;
            }
        }
        return false;
    }
}
