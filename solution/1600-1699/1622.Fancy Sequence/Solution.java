class Fancy {
    private int n;
    private SegmentTree tree;

    public Fancy() {
        tree = new SegmentTree(1, (int) 1e5 + 1);
    }
    
    public void append(int val) {
        ++n;
        tree.modifyAdd(n, n, val);
    }
    
    public void addAll(int inc) {
        tree.modifyAdd(1, n, inc);
    }
    
    public void multAll(int m) {
        tree.modifyMul(1, n, m);
    }
    
    public int getIndex(int idx) {
        return idx >= n ? -1 : tree.query(idx + 1, idx + 1);
    }
}

class SegmentTree {
    private int l;
    private int r;
    private SegmentTree left;
    private SegmentTree right;
    private long v;
    private long add;
    private long mul = 1;
    private static final int MOD = (int) 1e9 + 7;

    public SegmentTree(int l, int r) {
        this.l = l;
        this.r = r;
    }

    public void pushup() {
        v = (left().v + right().v) % MOD;
    }

    public int mid() {
        return (l + r) >> 1;
    }

    public SegmentTree left() {
        if (left == null) {
            left = new SegmentTree(l, mid());
        }
        return left;
    }

    public SegmentTree right() {
        if (right == null) {
            right = new SegmentTree(mid() + 1, r);
        }
        return right;
    }

    public void modifyAdd(int l, int r, int inc) {
        if (l > r) {
            return;
        }
        if (this.l >= l && this.r <= r) {
            v = (v + (this.r - this.l + 1) * inc) % MOD;
            add = (add + inc) % MOD;
            return;
        }
        pushdown();
        int mid = mid();
        if (l <= mid) {
            left().modifyAdd(l, r, inc);
        }
        if (r > mid) {
            right().modifyAdd(l, r, inc);
        }
        pushup();
    }

    public void modifyMul(int l, int r, int m) {
        if (l > r) {
            return;
        }
        if (this.l >= l && this.r <= r) {
            v = (v * m) % MOD;
            add = (add * m) % MOD;
            mul = (mul * m) % MOD;
            return;
        }
        pushdown();
        int mid = mid();
        if (l <= mid) {
            left().modifyMul(l, r, m);
        }
        if (r > mid) {
            right().modifyMul(l, r, m);
        }
        pushup();
    }

    public int query(int l, int r) {
        if (l > r) {
            return 0;
        }
        if (this.l >= l && this.r <= r) {
            return (int) v;
        }
        pushdown();
        int mid = mid();
        long v = 0;
        if (l <= mid) {
            v += left().query(l, r);
        }
        if (r > mid) {
            v += right().query(l, r);
        }
        return (int) v % MOD;
    }

    public void pushdown() {
        SegmentTree left = left(), right = right();
        if (add != 0 || mul != 1) {
            left.v = (left.v * mul + (left.r - left.l + 1) * add) % MOD;
            right.v = (right.v * mul + (right.r - right.l + 1) * add) % MOD;
            left.add = (left.add * mul + add) % MOD;
            right.add = (right.add * mul + add) % MOD;
            left.mul = (left.mul * mul) % MOD;
            right.mul = (right.mul * mul) % MOD;
            add = 0;
            mul = 1;
        }
    }
}

/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy obj = new Fancy();
 * obj.append(val);
 * obj.addAll(inc);
 * obj.multAll(m);
 * int param_4 = obj.getIndex(idx);
 */