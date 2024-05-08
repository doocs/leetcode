class Solution {
    private int m;
    private int n;
    private int[][] land;

    public int[] pondSizes(int[][] land) {
        m = land.length;
        n = land[0].length;
        this.land = land;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (land[i][j] == 0) {
                    ans.add(dfs(i, j));
                }
            }
        }
        return ans.stream().sorted().mapToInt(Integer::valueOf).toArray();
    }

    private int dfs(int i, int j) {
        int res = 1;
        land[i][j] = 1;
        for (int x = i - 1; x <= i + 1; ++x) {
            for (int y = j - 1; y <= j + 1; ++y) {
                if (x >= 0 && x < m && y >= 0 && y < n && land[x][y] == 0) {
                    res += dfs(x, y);
                }
            }
        }
        return res;
    }
}                              