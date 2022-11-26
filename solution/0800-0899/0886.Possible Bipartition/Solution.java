class Solution {
    private int[] p;

    public boolean possibleBipartition(int n, int[][] dislikes) {
        p = new int[n];
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (var e : dislikes) {
            int a = e[0] - 1, b = e[1] - 1;
            g[a].add(b);
            g[b].add(a);
        }
        for (int i = 0; i < n; ++i) {
            for (int j : g[i]) {
                if (find(i) == find(j)) {
                    return false;
                }
                p[find(j)] = find(g[i].get(0));
            }
        }
        return true;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}