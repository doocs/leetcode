class Solution {
    private int[] p;

    public boolean possibleBipartition(int n, int[][] dislikes) {
        p = new int[n];
        List<Integer>[] dis = new List[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            dis[i] = new ArrayList<>();
        }
        for (int[] d : dislikes) {
            int a = d[0] - 1, b = d[1] - 1;
            dis[a].add(b);
            dis[b].add(a);
        }
        for (int i = 0; i < n; ++i) {
            for (int j : dis[i]) {
                if (find(i) == find(j)) {
                    return false;
                }
                p[find(j)] = find(dis[i].get(0));
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