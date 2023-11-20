class BinaryIndexedTree {
    private final int inf = 1 << 30;
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
        Arrays.fill(c, inf);
    }

    public void update(int x, int v) {
        while (x <= n) {
            c[x] = Math.min(c[x], v);
            x += x & -x;
        }
    }

    public int query(int x) {
        int mi = inf;
        while (x > 0) {
            mi = Math.min(mi, c[x]);
            x -= x & -x;
        }
        return mi == inf ? -1 : mi;
    }
}

class Solution {
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int n = heights.length;
        int m = queries.length;
        for (int i = 0; i < m; ++i) {
            if (queries[i][0] > queries[i][1]) {
                queries[i] = new int[] {queries[i][1], queries[i][0]};
            }
        }
        Integer[] idx = new Integer[m];
        for (int i = 0; i < m; ++i) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> queries[j][1] - queries[i][1]);
        int[] s = heights.clone();
        Arrays.sort(s);
        int[] ans = new int[m];
        int j = n - 1;
        BinaryIndexedTree tree = new BinaryIndexedTree(n);
        for (int i : idx) {
            int l = queries[i][0], r = queries[i][1];
            while (j > r) {
                int k = n - Arrays.binarySearch(s, heights[j]) + 1;
                tree.update(k, j);
                --j;
            }
            if (l == r || heights[l] < heights[r]) {
                ans[i] = r;
            } else {
                int k = n - Arrays.binarySearch(s, heights[l]);
                ans[i] = tree.query(k);
            }
        }
        return ans;
    }
}