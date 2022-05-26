class Solution {
    private int[] p;

    public int makeConnected(int n, int[][] connections) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        int cnt = 0;
        for (int[] e : connections) {
            int a = e[0];
            int b = e[1];
            if (find(a) == find(b)) {
                ++cnt;
            } else {
                p[find(a)] = find(b);
                --n;
            }
        }
        return n - 1 > cnt ? -1 : n - 1;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}