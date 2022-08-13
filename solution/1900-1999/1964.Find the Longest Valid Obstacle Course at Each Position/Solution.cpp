class BinaryIndexedTree {
public:
    int n;
    vector<int> c;

    BinaryIndexedTree(int _n)
        : n(_n)
        , c(_n + 1) { }

    void update(int x, int val) {
        while (x <= n) {
            c[x] = max(c[x], val);
            x += lowbit(x);
        }
    }

    int query(int x) {
        int s = 0;
        while (x > 0) {
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
    vector<int> longestObstacleCourseAtEachPosition(vector<int>& obstacles) {
        set<int> s(obstacles.begin(), obstacles.end());
        int idx = 1;
        unordered_map<int, int> m;
        for (int v : s) m[v] = idx++;
        BinaryIndexedTree* tree = new BinaryIndexedTree(m.size());
        int n = obstacles.size();
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            int v = obstacles[i];
            int x = m[v];
            ans[i] = 1 + tree->query(x);
            tree->update(x, ans[i]);
        }
        return ans;
    }
};