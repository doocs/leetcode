class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
    }

    public void update(int x, int v) {
        while (x <= n) {
            c[x] = Math.max(c[x], v);
            x += x & -x;
        }
    }

    public int query(int x) {
        int mx = 0;
        while (x > 0) {
            mx = Math.max(mx, c[x]);
            x -= x & -x;
        }
        return mx;
    }
}

class Solution {
    public List<Boolean> getResults(int[][] queries) {
        int m = 0;
        for (int[] q : queries) {
            m = Math.max(m, q[1]);
        }
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(0);
        ts.add(m + 1);
        for (int[] q : queries) {
            if (q[0] == 1) {
                ts.add(q[1]);
            }
        }
        BinaryIndexedTree tree = new BinaryIndexedTree(m + 1);
        int pre = 0;
        for (int x : ts) {
            if (x > 0) {
                tree.update(x, x - pre);
            }
            pre = x;
        }
        List<Boolean> ans = new ArrayList<>();
        for (int i = queries.length - 1; i >= 0; --i) {
            int[] q = queries[i];
            int x = q[1];
            if (q[0] == 1) {
                int nxt = ts.higher(x);
                tree.update(nxt, nxt - ts.lower(x));
                ts.remove(x);
            } else {
                int p = ts.floor(x);
                ans.add(tree.query(p) >= q[2] || x - p >= q[2]);
            }
        }
        Collections.reverse(ans);
        return ans;
    }
}
