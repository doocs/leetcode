class Node {
    int l, r;
    long s00, s01, s10, s11;

    Node(int l, int r) {
        this.l = l;
        this.r = r;
        this.s00 = this.s01 = this.s10 = this.s11 = 0;
    }
}

class SegmentTree {
    Node[] tr;

    SegmentTree(int n) {
        tr = new Node[n * 4];
        build(1, 1, n);
    }

    void build(int u, int l, int r) {
        tr[u] = new Node(l, r);
        if (l == r) {
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
    }

    long query(int u, int l, int r) {
        if (tr[u].l >= l && tr[u].r <= r) {
            return tr[u].s11;
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        long ans = 0;
        if (r <= mid) {
            ans = query(u << 1, l, r);
        }
        if (l > mid) {
            ans = Math.max(ans, query(u << 1 | 1, l, r));
        }
        return ans;
    }

    void pushup(int u) {
        Node left = tr[u << 1];
        Node right = tr[u << 1 | 1];
        tr[u].s00 = Math.max(left.s00 + right.s10, left.s01 + right.s00);
        tr[u].s01 = Math.max(left.s00 + right.s11, left.s01 + right.s01);
        tr[u].s10 = Math.max(left.s10 + right.s10, left.s11 + right.s00);
        tr[u].s11 = Math.max(left.s10 + right.s11, left.s11 + right.s01);
    }

    void modify(int u, int x, int v) {
        if (tr[u].l == tr[u].r) {
            tr[u].s11 = Math.max(0, v);
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
}

class Solution {
    public int maximumSumSubsequence(int[] nums, int[][] queries) {
        int n = nums.length;
        SegmentTree tree = new SegmentTree(n);
        for (int i = 0; i < n; ++i) {
            tree.modify(1, i + 1, nums[i]);
        }
        long ans = 0;
        final int mod = (int) 1e9 + 7;
        for (int[] q : queries) {
            tree.modify(1, q[0] + 1, q[1]);
            ans = (ans + tree.query(1, 1, n)) % mod;
        }
        return (int) ans;
    }
}
