class BinaryIndexedTree {
public:
    int n;
    vector<int> c;

    BinaryIndexedTree(int _n)
        : n(_n)
        , c(_n + 1) {}

    void update(int x, int delta) {
        for (; x <= n; x += x & -x) {
            c[x] += delta;
        }
    }

    int query(int x) {
        int s = 0;
        for (; x; x -= x & -x) {
            s += c[x];
        }
        return s;
    }
};

class Solution {
public:
    int kEmptySlots(vector<int>& bulbs, int k) {
        int n = bulbs.size();
        BinaryIndexedTree* tree = new BinaryIndexedTree(n);
        bool vis[n + 1];
        memset(vis, false, sizeof(vis));
        for (int i = 1; i <= n; ++i) {
            int x = bulbs[i - 1];
            tree->update(x, 1);
            vis[x] = true;
            int y = x - k - 1;
            if (y > 0 && vis[y] && tree->query(x - 1) - tree->query(y) == 0) {
                return i;
            }
            y = x + k + 1;
            if (y <= n && vis[y] && tree->query(y - 1) - tree->query(x) == 0) {
                return i;
            }
        }
        return -1;
    }
};