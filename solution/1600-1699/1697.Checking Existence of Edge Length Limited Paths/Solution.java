class Solution {
    private int[] p;

    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        int m = queries.length;
        Integer[] indexes = new Integer[m];
        for (int i = 0; i < m; ++i) {
            indexes[i] = i;
        }
        Arrays.sort(indexes, Comparator.comparingInt(i -> queries[i][2]));
        Arrays.sort(edgeList, Comparator.comparingInt(a -> a[2]));
        boolean[] ans = new boolean[m];
        int i = 0;
        for (int j : indexes) {
            int pj = queries[j][0], qj = queries[j][1], limit = queries[j][2];
            while (i < edgeList.length && edgeList[i][2] < limit) {
                int u = edgeList[i][0], v = edgeList[i][1];
                p[find(u)] = find(v);
                ++i;
            }
            ans[j] = find(pj) == find(qj);
        }
        return ans;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}