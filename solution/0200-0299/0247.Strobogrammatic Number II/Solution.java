class Solution {
    private int n;

    public List<String> findStrobogrammatic(int n) {
        this.n = n;
        return dfs(n);
    }

    private List<String> dfs(int u) {
        if (u == 0) {
            return Collections.singletonList("");
        }
        if (u == 1) {
            return Arrays.asList("0", "1", "8");
        }
        List<String> ans = new ArrayList<>();
        int[][] pairs = new int[][]{{1, 1}, {8, 8}, {6, 9}, {9, 6}};
        for (String v : dfs(u - 2)) {
            for (int[] p : pairs) {
                ans.add(p[0] + v + p[1]);
            }
            if (u != n) {
                ans.add("0" + v + "0");
            }
        }
        return ans;
    }
}