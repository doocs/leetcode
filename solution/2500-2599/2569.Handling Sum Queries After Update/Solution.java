class Node {
    int l, r;
    int s, lazy;
}

class SegmentTree {
    private Node[] tr;
    private int[] nums;

    public SegmentTree(int[] nums) {
        int n = nums.length;
        this.nums = nums;
        tr = new Node[n << 2];
        for (int i = 0; i < tr.length; ++i) {
            tr[i] = new Node();
        }
        build(1, 1, n);
    }

    private void build(int u, int l, int r) {
        tr[u].l = l;
        tr[u].r = r;
        if (l == r) {
            tr[u].s = nums[l - 1];
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        pushup(u);
    }

    public void modify(int u, int l, int r) {
        if (tr[u].l >= l && tr[u].r <= r) {
            tr[u].lazy ^= 1;
            tr[u].s = tr[u].r - tr[u].l + 1 - tr[u].s;
            return;
        }
        pushdown(u);
        int mid = (tr[u].l + tr[u].r) >> 1;
        if (l <= mid) {
            modify(u << 1, l, r);
        }
        if (r > mid) {
            modify(u << 1 | 1, l, r);
        }
        pushup(u);
    }

    public int query(int u, int l, int r) {
        if (tr[u].l >= l && tr[u].r <= r) {
            return tr[u].s;
        }
        pushdown(u);
        int mid = (tr[u].l + tr[u].r) >> 1;
        int res = 0;
        if (l <= mid) {
            res += query(u << 1, l, r);
        }
        if (r > mid) {
            res += query(u << 1 | 1, l, r);
        }
        return res;
    }

    private void pushup(int u) {
        tr[u].s = tr[u << 1].s + tr[u << 1 | 1].s;
    }

    private void pushdown(int u) {
        if (tr[u].lazy == 1) {
            int mid = (tr[u].l + tr[u].r) >> 1;
            tr[u << 1].s = mid - tr[u].l + 1 - tr[u << 1].s;
            tr[u << 1].lazy ^= 1;
            tr[u << 1 | 1].s = tr[u].r - mid - tr[u << 1 | 1].s;
            tr[u << 1 | 1].lazy ^= 1;
            tr[u].lazy ^= 1;
        }
    }
}

class Solution {
    public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
        SegmentTree tree = new SegmentTree(nums1);
        long s = 0;
        for (int x : nums2) {
            s += x;
        }
        int m = 0;
        for (var q : queries) {
            if (q[0] == 3) {
                ++m;
            }
        }
        long[] ans = new long[m];
        int i = 0;
        for (var q : queries) {
            if (q[0] == 1) {
                tree.modify(1, q[1] + 1, q[2] + 1);
            } else if (q[0] == 2) {
                s += 1L * q[1] * tree.query(1, 1, nums2.length);
            } else {
                ans[i++] = s;
            }
        }
        return ans;
    }
}