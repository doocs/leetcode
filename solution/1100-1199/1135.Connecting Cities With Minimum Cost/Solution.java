class Solution {
    private int[] p;

    public int minimumCost(int n, int[][] connections) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        Arrays.sort(connections, (a, b) -> a[2] - b[2]);
        int res = 0;
        for (int[] e : connections) {
            if (union(e[0], e[1])) {
                res += e[2];
                --n;
                if (n == 1) {
                    return res;
                }
            }
        }
        return -1;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    private boolean union(int a, int b) {
        int pa = find(a - 1), pb = find(b - 1);
        if (pa == pb) {
            return false;
        }
        p[pa] = pb;
        return true;
    }
}