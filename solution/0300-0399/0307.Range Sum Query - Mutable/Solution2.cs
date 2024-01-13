public class Node {
    public int l;
    public int r;
    public int v;
}

public class SegmentTree {
    private Node[] tr;
    private int[] nums;

    public SegmentTree(int[] nums) {
        this.nums = nums;
        int n = nums.Length;
        tr = new Node[n << 2];
        for (int i = 0; i < tr.Length; ++i) {
            tr[i] = new Node();
        }
        Build(1, 1, n);
    }

    public void Build(int u, int l, int r) {
        tr[u].l = l;
        tr[u].r = r;
        if (l == r) {
            tr[u].v = nums[l - 1];
            return;
        }
        int mid = (l + r) >> 1;
        Build(u << 1, l, mid);
        Build(u << 1 | 1, mid + 1, r);
        Pushup(u);
    }

    public void Modify(int u, int x, int v) {
        if (tr[u].l == x && tr[u].r == x) {
            tr[u].v = v;
            return;
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        if (x <= mid) {
            Modify(u << 1, x, v);
        } else {
            Modify(u << 1 | 1, x, v);
        }
        Pushup(u);
    }

    public int Query(int u, int l, int r) {
        if (tr[u].l >= l && tr[u].r <= r) {
            return tr[u].v;
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        int v = 0;
        if (l <= mid) {
            v += Query(u << 1, l, r);
        }
        if (r > mid) {
            v += Query(u << 1 | 1, l, r);
        }
        return v;
    }

    public void Pushup(int u) {
        tr[u].v = tr[u << 1].v + tr[u << 1 | 1].v;
    }
}

public class NumArray {
    private SegmentTree tree;

    public NumArray(int[] nums) {
        tree = new SegmentTree(nums);
    }

    public void Update(int index, int val) {
        tree.Modify(1, index + 1, val);
    }

    public int SumRange(int left, int right) {
        return tree.Query(1, left + 1, right + 1);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.Update(index,val);
 * int param_2 = obj.SumRange(left,right);
 */
