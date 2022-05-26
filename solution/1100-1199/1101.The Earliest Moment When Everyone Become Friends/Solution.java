class Solution {
    private int[] p;

    public int earliestAcq(int[][] logs, int n) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        Arrays.sort(logs, Comparator.comparingInt(a -> a[0]));
        for (int[] log : logs) {
            int t = log[0], a = log[1], b = log[2];
            if (find(a) == find(b)) {
                continue;
            }
            p[find(a)] = find(b);
            --n;
            if (n == 1) {
                return t;
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
}