class BinaryIndexedTree {
public:
    int n;
    vector<int> c;

    BinaryIndexedTree(int _n)
        : n(_n)
        , c(_n + 1) {}

    void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += lowbit(x);
        }
    }

    int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= lowbit(x);
        }
        return s;
    }

    int lowbit(int x) {
        return x & -x;
    }
};

class NumArray {
public:
    BinaryIndexedTree* tree;

    NumArray(vector<int>& nums) {
        int n = nums.size();
        tree = new BinaryIndexedTree(n);
        for (int i = 0; i < n; ++i) tree->update(i + 1, nums[i]);
    }

    void update(int index, int val) {
        int prev = sumRange(index, index);
        tree->update(index + 1, val - prev);
    }

    int sumRange(int left, int right) {
        return tree->query(right + 1) - tree->query(left);
    }
};

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray* obj = new NumArray(nums);
 * obj->update(index,val);
 * int param_2 = obj->sumRange(left,right);
 */