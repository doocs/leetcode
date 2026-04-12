class Node {
    int l, r;
    int g;

    Node(int l, int r) {
        this.l = l;
        this.r = r;
        this.g = 0;
    }
}

class SegmentTree {
    Node[] tr;

    SegmentTree(int n) {
        tr = new Node[n << 2];
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

    void pushup(int u) {
        tr[u].g = gcd(tr[u << 1].g, tr[u << 1 | 1].g);
    }

    void modify(int u, int x, int v) {
        if (tr[u].l == tr[u].r) {
            tr[u].g = v;
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

    int query(int u, int l, int r) {
        if (l > r) {
            return 0;
        }
        if (tr[u].l >= l && tr[u].r <= r) {
            return tr[u].g;
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        if (r <= mid) {
            return query(u << 1, l, r);
        }
        if (l > mid) {
            return query(u << 1 | 1, l, r);
        }
        return gcd(query(u << 1, l, mid), query(u << 1 | 1, mid + 1, r));
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}

class Solution {
    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public int countGoodSubseq(int[] nums, int p, int[][] queries) {
        int n = nums.length;
        SegmentTree tree = new SegmentTree(n);
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] % p == 0) {
                tree.modify(1, i + 1, nums[i]);
                ++cnt;
            }
        }

        int ans = 0;
        for (int[] q : queries) {
            int idx = q[0], val = q[1];
            if (nums[idx] % p == 0) {
                tree.modify(1, idx + 1, 0);
                --cnt;
            }
            if (val % p == 0) {
                tree.modify(1, idx + 1, val);
                ++cnt;
            }
            nums[idx] = val;

            if (tree.tr[1].g != p) {
                continue;
            }
            if (cnt < n || n > 6) {
                ++ans;
                continue;
            }
            for (int i = 1; i <= n; ++i) {
                int leftG = tree.query(1, 1, i - 1);
                int rightG = tree.query(1, i + 1, n);
                if (gcd(leftG, rightG) == p) {
                    ++ans;
                    break;
                }
            }
        }
        return ans;
    }
}