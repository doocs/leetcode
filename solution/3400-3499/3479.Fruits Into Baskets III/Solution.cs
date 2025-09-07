public class SegmentTree {
    int[] nums;
    int[] tr;

    public SegmentTree(int[] nums) {
        this.nums = nums;
        int n = nums.Length;
        this.tr = new int[n << 2];
        Build(1, 1, n);
    }

    public void Build(int u, int l, int r) {
        if (l == r) {
            tr[u] = nums[l - 1];
            return;
        }
        int mid = (l + r) >> 1;
        Build(u << 1, l, mid);
        Build(u << 1 | 1, mid + 1, r);
        Pushup(u);
    }

    public void Modify(int u, int l, int r, int i, int v) {
        if (l == r) {
            tr[u] = v;
            return;
        }
        int mid = (l + r) >> 1;
        if (i <= mid) {
            Modify(u << 1, l, mid, i, v);
        } else {
            Modify(u << 1 | 1, mid + 1, r, i, v);
        }
        Pushup(u);
    }

    public int Query(int u, int l, int r, int v) {
        if (tr[u] < v) {
            return -1;
        }
        if (l == r) {
            return l;
        }
        int mid = (l + r) >> 1;
        if (tr[u << 1] >= v) {
            return Query(u << 1, l, mid, v);
        }
        return Query(u << 1 | 1, mid + 1, r, v);
    }

    public void Pushup(int u) {
        tr[u] = Math.Max(tr[u << 1], tr[u << 1 | 1]);
    }
}

public class Solution {
    public int NumOfUnplacedFruits(int[] fruits, int[] baskets) {
        SegmentTree tree = new SegmentTree(baskets);
        int n = baskets.Length;
        int ans = 0;
        foreach (var x in fruits) {
            int i = tree.Query(1, 1, n, x);
            if (i < 0) {
                ans++;
            } else {
                tree.Modify(1, 1, n, i, 0);
            }
        }
        return ans;
    }
}
