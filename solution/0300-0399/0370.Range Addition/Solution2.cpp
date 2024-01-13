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
    vector<int> getModifiedArray(int length, vector<vector<int>>& updates) {
        BinaryIndexedTree* tree = new BinaryIndexedTree(length);
        for (auto& e : updates) {
            int start = e[0], end = e[1], inc = e[2];
            tree->update(start + 1, inc);
            tree->update(end + 2, -inc);
        }
        vector<int> ans;
        for (int i = 0; i < length; ++i) ans.push_back(tree->query(i + 1));
        return ans;
    }
};