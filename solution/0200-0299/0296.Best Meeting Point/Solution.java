class Solution {
    public int minTotalDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        Collections.sort(cols);
        int i = rows.get(rows.size() >> 1);
        int j = cols.get(cols.size() >> 1);
        return f(rows, i) + f(cols, j);
    }

    private int f(List<Integer> arr, int x) {
        int s = 0;
        for (int v : arr) {
            s += Math.abs(v - x);
        }
        return s;
    }
}