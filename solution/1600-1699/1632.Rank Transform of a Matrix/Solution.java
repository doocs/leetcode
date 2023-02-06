class UnionFind {
    private int[] p;
    private int[] size;

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

    public void union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa != pb) {
            if (size[pa] > size[pb]) {
                p[pb] = pa;
                size[pa] += size[pb];
            } else {
                p[pa] = pb;
                size[pb] += size[pa];
            }
        }
    }

    public void reset(int x) {
        p[x] = x;
        size[x] = 1;
    }
}

class Solution {
    public int[][] matrixRankTransform(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        TreeMap<Integer, List<int[]>> d = new TreeMap<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                d.computeIfAbsent(matrix[i][j], k -> new ArrayList<>()).add(new int[] {i, j});
            }
        }
        int[] rowMax = new int[m];
        int[] colMax = new int[n];
        int[][] ans = new int[m][n];
        UnionFind uf = new UnionFind(m + n);
        int[] rank = new int[m + n];
        for (var ps : d.values()) {
            for (var p : ps) {
                uf.union(p[0], p[1] + m);
            }
            for (var p : ps) {
                int i = p[0], j = p[1];
                rank[uf.find(i)] = Math.max(rank[uf.find(i)], Math.max(rowMax[i], colMax[j]));
            }
            for (var p : ps) {
                int i = p[0], j = p[1];
                ans[i][j] = 1 + rank[uf.find(i)];
                rowMax[i] = ans[i][j];
                colMax[j] = ans[i][j];
            }
            for (var p : ps) {
                uf.reset(p[0]);
                uf.reset(p[1] + m);
            }
        }
        return ans;
    }
}