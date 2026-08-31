class BinaryIndexedTree {
private:
    int n;
    vector<int> c;

public:
    BinaryIndexedTree(int n) {
        this->n = n;
        c.resize(n + 1);
    }

    void update(int x, int v) {
        while (x <= n) {
            c[x] = max(c[x], v);
            x += x & -x;
        }
    }

    int query(int x) {
        int mx = 0;
        while (x > 0) {
            mx = max(mx, c[x]);
            x -= x & -x;
        }
        return mx;
    }
};

class Solution {
public:
    vector<bool> getResults(vector<vector<int>>& queries) {
        int m = 0;
        for (auto& q : queries) {
            m = max(m, q[1]);
        }
        set<int> ts{0, m + 1};
        for (auto& q : queries) {
            if (q[0] == 1) {
                ts.insert(q[1]);
            }
        }
        BinaryIndexedTree tree(m + 1);
        int pre = 0;
        for (int x : ts) {
            if (x) {
                tree.update(x, x - pre);
            }
            pre = x;
        }
        vector<bool> ans;
        for (int i = queries.size() - 1; i >= 0; --i) {
            int x = queries[i][1];
            if (queries[i][0] == 1) {
                auto it = ts.find(x);
                tree.update(*next(it), *next(it) - *prev(it));
                ts.erase(it);
            } else {
                auto it = prev(ts.upper_bound(x));
                ans.push_back(tree.query(*it) >= queries[i][2] || x - *it >= queries[i][2]);
            }
        }
        ranges::reverse(ans);
        return ans;
    }
};
