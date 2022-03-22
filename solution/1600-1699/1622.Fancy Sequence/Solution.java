class Node {
    Node left;
    Node right;
    int l;
    int r;
    int mid;
    long v;
    long add;
    long mul = 1;

    public Node(int l, int r) {
        this.l = l;
        this.r = r;
        this.mid = (l + r) >> 1;
    }
}

class SegmentTree {
    private Node root = new Node(1, (int) 1e5 + 1);
    private static final int MOD = (int) 1e9 + 7;

    public SegmentTree() {

    }

    public void modifyAdd(int l, int r, int inc) {
        modifyAdd(l, r, inc, root);
    }

    public void modifyAdd(int l, int r, int inc, Node node) {
        if (l > r) {
            return;
        }
        if (node.l >= l && node.r <= r) {
            node.v = (node.v + (node.r - node.l + 1) * inc) % MOD;
            node.add = (node.add + inc) % MOD;
            return;
        }
        pushdown(node);
        if (l <= node.mid) {
            modifyAdd(l, r, inc, node.left);
        }
        if (r > node.mid) {
            modifyAdd(l, r, inc, node.right);
        }
        pushup(node);
    }

    public void modifyMul(int l, int r, int m) {
        modifyMul(l, r, m, root);
    }

    public void modifyMul(int l, int r, int m, Node node) {
        if (l > r) {
            return;
        }
        if (node.l >= l && node.r <= r) {
            node.v = (node.v * m) % MOD;
            node.add = (node.add * m) % MOD;
            node.mul = (node.mul * m) % MOD;
            return;
        }
        pushdown(node);
        if (l <= node.mid) {
            modifyMul(l, r, m, node.left);
        }
        if (r > node.mid) {
            modifyMul(l, r, m, node.right);
        }
        pushup(node);
    }

    public int query(int l, int r) {
        return query(l, r, root);
    }

    public int query(int l, int r, Node node) {
        if (l > r) {
            return 0;
        }
        if (node.l >= l && node.r <= r) {
            return (int) node.v;
        }
        pushdown(node);
        int v = 0;
        if (l <= node.mid) {
            v = (v + query(l, r, node.left)) % MOD;
        }
        if (r > node.mid) {
            v = (v + query(l, r, node.right)) % MOD;
        }
        return v;
    }

    public void pushup(Node node) {
        node.v = (node.left.v + node.right.v) % MOD;
    }

    public void pushdown(Node node) {
        if (node.left == null) {
            node.left = new Node(node.l, node.mid);
        }
        if (node.right == null) {
            node.right = new Node(node.mid + 1, node.r);
        }
        if (node.add != 0 || node.mul != 1) {
            Node left = node.left, right = node.right;
            left.v = (left.v * node.mul + (left.r - left.l + 1) * node.add) % MOD;
            right.v = (right.v * node.mul + (right.r - right.l + 1) * node.add) % MOD;
            left.add = (left.add * node.mul + node.add) % MOD;
            right.add = (right.add * node.mul + node.add) % MOD;
            left.mul = (left.mul * node.mul) % MOD;
            right.mul = (right.mul * node.mul) % MOD;
            node.add = 0;
            node.mul = 1;
        }
    }
}

class Fancy {
    private int n;
    private SegmentTree tree = new SegmentTree();

    public Fancy() {

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

/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy obj = new Fancy();
 * obj.append(val);
 * obj.addAll(inc);
 * obj.multAll(m);
 * int param_4 = obj.getIndex(idx);
 */