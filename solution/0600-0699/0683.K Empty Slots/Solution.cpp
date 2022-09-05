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
    int kEmptySlots(vector<int>& bulbs, int k) {
        int n = bulbs.size();
        BinaryIndexedTree* tree = new BinaryIndexedTree(n);
        for (int i = 0; i < n; ++i) {
            int x = bulbs[i];
            tree->update(x, 1);
            bool case1 = x - k - 1 > 0 && tree->query(x - k - 1) - tree->query(x - k - 2) == 1 && tree->query(x - 1) - tree->query(x - k - 1) == 0;
            bool case2 = x + k + 1 <= n && tree->query(x + k + 1) - tree->query(x + k) == 1 && tree->query(x + k) - tree->query(x) == 0;
            if (case1 || case2) return i + 1;
        }
        return -1;
    }
};