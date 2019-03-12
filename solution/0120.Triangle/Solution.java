class Solution {
    private int[][] cache = null;
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        cache = new int[n][triangle.get(n - 1).size()];
        for (int[] row : cache) Arrays.fill(row, -1);
        return dfs(triangle,0,0);
    }
    private int dfs(List<List<Integer>> triangle, int row, int col) {
        if (row == triangle.size()) return 0;
        if (cache[row][col] != -1) return cache[row][col];
        int l = dfs(triangle,row+1,col);
        int r = dfs(triangle,row+1,col+1);
        int res = Math.min(l,r)+triangle.get(row).get(col);
        cache[row][col] = res;
        return res;
    }
}