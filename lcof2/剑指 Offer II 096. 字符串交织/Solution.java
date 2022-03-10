class Solution {
    private int m;
    private int n;
    private String s1;
    private String s2;
    private String s3;
    private Map<Integer, Boolean> memo = new HashMap<>();

    public boolean isInterleave(String s1, String s2, String s3) {
        m = s1.length();
        n = s2.length();
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        if (m + n != s3.length()) {
            return false;
        }
        return dfs(0, 0);
    }

    private boolean dfs(int i, int j) {
        System.out.println(i + ", " + j);
        if (i == m && j == n) {
            return true;
        }
        if (memo.containsKey(i * 100 + j)) {
            return memo.get(i * 100 + j);
        }

        boolean ret = (i < m && s1.charAt(i) == s3.charAt(i + j) && dfs(i + 1, j)) ||
                (j < n && s2.charAt(j) == s3.charAt(i + j) && dfs(i, j + 1));

        memo.put(i * 100 + j, ret);
        return ret;
    }
}
