class SegmentTree {
    int[] nums;
    int[] tr;

    public SegmentTree(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        this.tr = new int[n << 2];
        build(1, 1, n);
    }

    public void build(int u, int l, int r) {
        if (l == r) {
            tr[u] = nums[l - 1];
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        pushup(u);
    }

    public void modify(int u, int l, int r, int i, int v) {
        if (l == r) {
            tr[u] = v;
            return;
        }
        int mid = (l + r) >> 1;
        if (i <= mid) {
            modify(u << 1, l, mid, i, v);
        } else {
            modify(u << 1 | 1, mid + 1, r, i, v);
        }
        pushup(u);
    }

    public int query(int u, int l, int r, int v) {
        if (tr[u] < v) {
            return -1;
        }
        if (l == r) {
            return l;
        }
        int mid = (l + r) >> 1;
        if (tr[u << 1] >= v) {
            return query(u << 1, l, mid, v);
        }
        return query(u << 1 | 1, mid + 1, r, v);
    }

    public void pushup(int u) {
        tr[u] = Math.max(tr[u << 1], tr[u << 1 | 1]);
    }
}

class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        SegmentTree tree = new SegmentTree(baskets);
        int n = baskets.length;
        int ans = 0;
        for (int x : fruits) {
            int i = tree.query(1, 1, n, x);
            if (i < 0) {
                ans++;
            } else {
                tree.modify(1, 1, n, i, 0);
            }
        }
        return ans;
    }
}
