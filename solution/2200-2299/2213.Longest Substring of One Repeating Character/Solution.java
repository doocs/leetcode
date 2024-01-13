class Node {
    int l;
    int r;
    int size;
    int lmx;
    int rmx;
    int mx;
    char lc;
    char rc;
}

class SegmentTree {
    private String s;
    private Node[] tr;

    public SegmentTree(String s) {
        int n = s.length();
        this.s = s;
        tr = new Node[n << 2];
        for (int i = 0; i < tr.length; ++i) {
            tr[i] = new Node();
        }
        build(1, 1, n);
    }

    public void build(int u, int l, int r) {
        tr[u].l = l;
        tr[u].r = r;
        if (l == r) {
            tr[u].lmx = 1;
            tr[u].rmx = 1;
            tr[u].mx = 1;
            tr[u].size = 1;
            tr[u].lc = s.charAt(l - 1);
            tr[u].rc = s.charAt(l - 1);
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        pushup(u);
    }

    void modify(int u, int x, char v) {
        if (tr[u].l == x && tr[u].r == x) {
            tr[u].lc = v;
            tr[u].rc = v;
            return;
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        if (x <= mid) {
            modify(u << 1, x, v);
        } else {
            modify(u << 1 | 1, x, v);
        }
        pushup(u);
    }

    Node query(int u, int l, int r) {
        if (tr[u].l >= l && tr[u].r <= r) {
            return tr[u];
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        if (r <= mid) {
            return query(u << 1, l, r);
        }
        if (l > mid) {
            return query(u << 1 | 1, l, r);
        }
        Node ans = new Node();
        Node left = query(u << 1, l, r);
        Node right = query(u << 1 | 1, l, r);
        pushup(ans, left, right);
        return ans;
    }

    void pushup(Node root, Node left, Node right) {
        root.lc = left.lc;
        root.rc = right.rc;
        root.size = left.size + right.size;

        root.mx = Math.max(left.mx, right.mx);
        root.lmx = left.lmx;
        root.rmx = right.rmx;

        if (left.rc == right.lc) {
            if (left.lmx == left.size) {
                root.lmx += right.lmx;
            }
            if (right.rmx == right.size) {
                root.rmx += left.rmx;
            }
            root.mx = Math.max(root.mx, left.rmx + right.lmx);
        }
    }

    void pushup(int u) {
        pushup(tr[u], tr[u << 1], tr[u << 1 | 1]);
    }
}

class Solution {
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        SegmentTree tree = new SegmentTree(s);
        int k = queryCharacters.length();
        int[] ans = new int[k];
        for (int i = 0; i < k; ++i) {
            int x = queryIndices[i] + 1;
            char c = queryCharacters.charAt(i);
            tree.modify(1, x, c);
            ans[i] = tree.query(1, 1, s.length()).mx;
        }
        return ans;
    }
}