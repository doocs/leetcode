class UnionFind {
    private final int[] p;
    private final int[] size;

    public UnionFind(int n) {
        p = new int[n];
        size = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            size[i] = 1;
        }
    }

    public int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    public boolean union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) {
            return false;
        }
        if (size[pa] > size[pb]) {
            p[pb] = pa;
            size[pa] += size[pb];
        } else {
            p[pa] = pb;
            size[pb] += size[pa];
        }
        return true;
    }
}

class Solution {
    public int latestDayToCross(int row, int col, int[][] cells) {
        int mn = cells.length;
        UnionFind uf = new UnionFind(mn + 2);
        int s = mn, t = mn + 1;
        int[][] g = new int[row][col];
        for (var e : g) {
            Arrays.fill(e, 1);
        }
        final int[] dirs = {-1, 0, 1, 0, -1};
        for (int i = mn - 1;; --i) {
            int x = cells[i][0] - 1, y = cells[i][1] - 1;
            g[x][y] = 0;
            for (int j = 0; j < 4; ++j) {
                int nx = x + dirs[j], ny = y + dirs[j + 1];
                if (nx >= 0 && nx < row && ny >= 0 && ny < col && g[nx][ny] == 0) {
                    uf.union(x * col + y, nx * col + ny);
                }
            }
            if (x == 0) {
                uf.union(s, x * col + y);
            }
            if (x == row - 1) {
                uf.union(t, x * col + y);
            }
            if (uf.find(s) == uf.find(t)) {
                return i;
            }
        }
    }
}
