class BinaryIndexedTree {
public:
    int n;
    vector<int> c;

    BinaryIndexedTree(int _n)
        : n(_n)
        , c(_n + 1) { }

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

class Solution {
public:
    vector<int> countSmaller(vector<int>& nums) {
        unordered_set<int> s(nums.begin(), nums.end());
        vector<int> alls(s.begin(), s.end());
        sort(alls.begin(), alls.end());
        unordered_map<int, int> m;
        int n = alls.size();
        for (int i = 0; i < n; ++i) m[alls[i]] = i + 1;
        BinaryIndexedTree* tree = new BinaryIndexedTree(n);
        vector<int> ans(nums.size());
        for (int i = nums.size() - 1; i >= 0; --i) {
            int x = m[nums[i]];
            tree->update(x, 1);
            ans[i] = tree->query(x - 1);
        }
        return ans;
    }
};