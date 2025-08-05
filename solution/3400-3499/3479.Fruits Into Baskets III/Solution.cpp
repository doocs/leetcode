class SegmentTree {
public:
    vector<int> nums, tr;

    SegmentTree(vector<int>& nums) {
        this->nums = nums;
        int n = nums.size();
        tr.resize(n * 4);
        build(1, 1, n);
    }

    void build(int u, int l, int r) {
        if (l == r) {
            tr[u] = nums[l - 1];
            return;
        }
        int mid = (l + r) >> 1;
        build(u * 2, l, mid);
        build(u * 2 + 1, mid + 1, r);
        pushup(u);
    }

    void modify(int u, int l, int r, int i, int v) {
        if (l == r) {
            tr[u] = v;
            return;
        }
        int mid = (l + r) >> 1;
        if (i <= mid) {
            modify(u * 2, l, mid, i, v);
        } else {
            modify(u * 2 + 1, mid + 1, r, i, v);
        }
        pushup(u);
    }

    int query(int u, int l, int r, int v) {
        if (tr[u] < v) {
            return -1;
        }
        if (l == r) {
            return l;
        }
        int mid = (l + r) >> 1;
        if (tr[u * 2] >= v) {
            return query(u * 2, l, mid, v);
        }
        return query(u * 2 + 1, mid + 1, r, v);
    }

    void pushup(int u) {
        tr[u] = max(tr[u * 2], tr[u * 2 + 1]);
    }
};

class Solution {
public:
    int numOfUnplacedFruits(vector<int>& fruits, vector<int>& baskets) {
        SegmentTree tree(baskets);
        int n = baskets.size();
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
};
