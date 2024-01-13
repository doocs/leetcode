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
    vector<int> processQueries(vector<int>& queries, int m) {
        int n = queries.size();
        vector<int> pos(m + 1);
        BinaryIndexedTree* tree = new BinaryIndexedTree(m + n);
        for (int i = 1; i <= m; ++i) {
            pos[i] = n + i;
            tree->update(n + i, 1);
        }
        vector<int> ans;
        for (int i = 0; i < n; ++i) {
            int v = queries[i];
            int j = pos[v];
            tree->update(j, -1);
            ans.push_back(tree->query(j));
            pos[v] = n - i;
            tree->update(n - i, 1);
        }
        return ans;
    }
};