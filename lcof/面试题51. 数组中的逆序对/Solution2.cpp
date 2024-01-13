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
            x += x & -x;
        }
    }

    int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }
};

class Solution {
public:
    int reversePairs(vector<int>& nums) {
        vector<int> alls = nums;
        sort(alls.begin(), alls.end());
        alls.erase(unique(alls.begin(), alls.end()), alls.end());
        BinaryIndexedTree tree(alls.size());
        int ans = 0;
        for (int i = nums.size() - 1; ~i; --i) {
            int x = lower_bound(alls.begin(), alls.end(), nums[i]) - alls.begin() + 1;
            ans += tree.query(x - 1);
            tree.update(x, 1);
        }
        return ans;
    }
};