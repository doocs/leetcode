class Solution {
    private int[] p;

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        int[][] all = new int[pipes.length + n][3];
        int idx = 0;
        for (int[] pipe : pipes) {
            all[idx++] = pipe;
        }
        for (int j = 0; j < n; ++j) {
            all[idx++] = new int[]{0, j + 1, wells[j]};
        }
        p = new int[n + 1];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        Arrays.sort(all, Comparator.comparingInt(a -> a[2]));
        int res = 0;
        for (int[] e : all) {
            if (find(e[0]) == find(e[1])) {
                continue;
            }
            p[find(e[0])] = find(e[1]);
            res += e[2];
            --n;
            if (n == 0) {
                break;
            }
        }
        return res;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}