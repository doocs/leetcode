class Solution {
    private int cnt;

    public int movingCount(int m, int n, int k) {
        cnt = 0;
        boolean[][] visited = new boolean[m][n];
        visit(0, 0, m, n, k, visited);
        return cnt;
    }

    private void visit(int i, int j, int m, int n, int k, boolean[][] visited) {
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || cal(i, j) > k) {
            return;
        }
        visited[i][j] = true;
        ++cnt;
        visit(i - 1, j, m, n, k, visited);
        visit(i + 1, j, m, n, k, visited);
        visit(i, j - 1, m, n, k, visited);
        visit(i, j + 1, m, n, k, visited);
    }

    private int cal(int i, int j) {
        return cal(i) + cal(j);
    }

    private int cal(int val) {
        int s = 0;
        while (val != 0) {
            s += (val % 10);
            val /= 10;
        }
        return s;
    }
}