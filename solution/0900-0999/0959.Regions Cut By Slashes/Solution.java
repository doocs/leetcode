class Solution {
    private int[] p;

    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        p = new int[n * n * 4];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        for (int i = 0; i < n; ++i) {
            char[] row = grid[i].toCharArray();
            for (int j = 0; j < n; ++j) {
                int idx = i * n + j;
                if (i < n - 1) {
                    p[find(idx * 4 + 2)] = find((idx + n) * 4);
                }
                if (j < n - 1) {
                    p[find(idx * 4 + 1)] = find((idx + 1) * 4 + 3);
                }

                if (row[j] == '/') {
                    p[find(idx * 4)] = find(idx * 4 + 3);
                    p[find(idx * 4 + 1)] = find(idx * 4 + 2);
                } else if (row[j] == '\\') {
                    p[find(idx * 4)] = find(idx * 4 + 1);
                    p[find(idx * 4 + 2)] = find(idx * 4 + 3);
                } else {
                    p[find(idx * 4)] = find(idx * 4 + 1);
                    p[find(idx * 4 + 1)] = find(idx * 4 + 2);
                    p[find(idx * 4 + 2)] = find(idx * 4 + 3);
                }
            }
        }
        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < p.length; ++i) {
            s.add(find(i));
        }
        return s.size();
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}