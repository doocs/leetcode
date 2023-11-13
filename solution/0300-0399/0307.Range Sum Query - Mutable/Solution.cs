class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
    }

    public void Update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += x & -x;
        }
    }

    public int Query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }
}

public class NumArray {
    private BinaryIndexedTree tree;

    public NumArray(int[] nums) {
        int n = nums.Length;
        tree = new BinaryIndexedTree(n);
        for (int i = 0; i < n; ++i) {
            tree.Update(i + 1, nums[i]);
        }
    }

    public void Update(int index, int val) {
        int prev = SumRange(index, index);
        tree.Update(index + 1, val - prev);
    }

    public int SumRange(int left, int right) {
        return tree.Query(right + 1) - tree.Query(left);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.Update(index,val);
 * int param_2 = obj.SumRange(left,right);
 */