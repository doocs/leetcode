class BinaryIndexedTree {
public:
    BinaryIndexedTree(int _n)
        : n(_n)
        , c(_n + 1) {}

    void update(int x, int v) {
        while (x <= n) {
            c[x] = max(c[x], v);
            x += x & -x;
        }
    }

    int query(int x) {
        int mx = 0;
        while (x) {
            mx = max(mx, c[x]);
            x -= x & -x;
        }
        return mx;
    }

private:
    int n;
    vector<int> c;
};

class Solution {
public:
    int lengthOfLIS(vector<int>& nums) {
        vector<int> s = nums;
        sort(s.begin(), s.end());
        s.erase(unique(s.begin(), s.end()), s.end());
        BinaryIndexedTree tree(s.size());
        for (int x : nums) {
            x = lower_bound(s.begin(), s.end(), x) - s.begin() + 1;
            int t = tree.query(x - 1) + 1;
            tree.update(x, t);
        }
        return tree.query(s.size());
    }
};