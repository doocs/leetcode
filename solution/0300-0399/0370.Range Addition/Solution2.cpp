class BinaryIndexedTree {
private:
    int n;
    vector<int> c;

public:
    BinaryIndexedTree(int n)
        : n(n)
        , c(n + 1) {}

    void update(int x, int delta) {
        for (; x <= n; x += x & -x) {
            c[x] += delta;
        }
    }

    int query(int x) {
        int s = 0;
        for (; x > 0; x -= x & -x) {
            s += c[x];
        }
        return s;
    }
};

class Solution {
public:
    vector<int> getModifiedArray(int length, vector<vector<int>>& updates) {
        BinaryIndexedTree* tree = new BinaryIndexedTree(length);
        for (const auto& e : updates) {
            int l = e[0], r = e[1], c = e[2];
            tree->update(l + 1, c);
            tree->update(r + 2, -c);
        }
        vector<int> ans;
        for (int i = 0; i < length; ++i) {
            ans.push_back(tree->query(i + 1));
        }
        return ans;
    }
};
