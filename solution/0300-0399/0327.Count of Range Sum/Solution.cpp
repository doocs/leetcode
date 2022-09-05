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

class Solution {
public:
    int countRangeSum(vector<int>& nums, int lower, int upper) {
        int n = nums.size();
        vector<long long> preSum(n + 1);
        for (int i = 0; i < n; ++i) preSum[i + 1] = preSum[i] + nums[i];
        set<long long> alls;
        for (auto& s : preSum) {
            alls.insert(s);
            alls.insert(s - upper);
            alls.insert(s - lower);
        }
        unordered_map<long long, int> m;
        int idx = 1;
        for (auto& v : alls) m[v] = idx++;
        BinaryIndexedTree* tree = new BinaryIndexedTree(m.size());
        int ans = 0;
        for (auto& s : preSum) {
            int i = m[s - upper], j = m[s - lower];
            ans += tree->query(j) - tree->query(i - 1);
            tree->update(m[s], 1);
        }
        return ans;
    }
};