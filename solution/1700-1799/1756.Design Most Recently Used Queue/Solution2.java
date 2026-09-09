class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        this.c = new int[n + 1];
    }

    public void update(int x, int v) {
        while (x <= n) {
            c[x] += v;
            x += x & -x;
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }
}

class MRUQueue {
    private int n;
    private int[] q;
    private BinaryIndexedTree tree;

    public MRUQueue(int n) {
        this.n = n;
        q = new int[n + 2010];
        for (int i = 1; i <= n; ++i) {
            q[i] = i;
        }
        tree = new BinaryIndexedTree(n + 2010);
    }

    public int fetch(int k) {
        int l = 1, r = n;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (mid - tree.query(mid) >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        int x = q[l];
        q[++n] = x;
        tree.update(l, 1);
        return x;
    }
}

/**
 * Your MRUQueue object will be instantiated and called as such:
 * MRUQueue obj = new MRUQueue(n);
 * int param_1 = obj.fetch(k);
 */