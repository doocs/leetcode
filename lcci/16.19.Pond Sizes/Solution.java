class Solution {
    private int[] p;
    private int[] size;

    public int[] pondSizes(int[][] land) {
        int m = land.length, n = land[0].length;
        p = new int[m * n];
        size = new int[m * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
            size[i] = 1;
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (land[i][j] != 0) {
                    continue;
                }
                int idx = i * n + j;
                if (i < m - 1 && land[i + 1][j] == 0) {
                    union(idx, (i + 1) * n + j);
                }
                if (j < n - 1 && land[i][j + 1] == 0) {
                    union(idx, i * n + j + 1);
                }
                if (i < m - 1 && j < n - 1 && land[i + 1][j + 1] == 0) {
                    union(idx, (i + 1) * n + j + 1);
                }
                if (i < m - 1 && j > 0 && land[i + 1][j - 1] == 0) {
                    union(idx, (i + 1) * n + j - 1);
                }
            }
        }
        Set<Integer> s = new HashSet<>();
        List<Integer> t = new ArrayList<>();
        for (int i = 0; i < m * n; ++i) {
            if (land[i / n][i % n] != 0) {
                continue;
            }
            int root = find(i);
            if (!s.contains(root)) {
                s.add(root);
                t.add(size[root]);
            }
        }
        Collections.sort(t);
        int[] res = new int[t.size()];
        for (int i = 0; i < res.length; ++i) {
            res[i] = t.get(i);
        }
        return res;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    private void union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) {
            return;
        }
        size[pb] += size[pa];
        p[pa] = pb;
    }
}