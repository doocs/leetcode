class BinaryIndexedTree {
public:
    int n;
    vector<int> c;

    BinaryIndexedTree(int _n): n(_n), c(_n + 1){}

    void update(int x, int val) {
        while (x <= n)
        {
            c[x] = max(c[x], val);
            x += lowbit(x);
        }
    }

    int query(int x) {
        int s = 0;
        while (x > 0)
        {
            s = max(s, c[x]);
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
    int lengthOfLIS(vector<int>& nums) {
        set<int> s(nums.begin(), nums.end());
        int idx = 1;
        unordered_map<int, int> m;
        for (int v : s) m[v] = idx++;
        BinaryIndexedTree* tree = new BinaryIndexedTree(m.size());
        int ans = 1;
        for (int v : nums)
        {
            int x = m[v];
            int t = tree->query(x - 1) + 1;
            ans = max(ans, t);
            tree->update(x, t);
        }
        return ans;
    }
};