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
    int createSortedArray(vector<int>& instructions) {
        int n = 100010;
        int mod = 1e9 + 7;
        BinaryIndexedTree* tree = new BinaryIndexedTree(n);
        int ans = 0;
        for (int num : instructions) {
            int a = tree->query(num - 1);
            int b = tree->query(n) - tree->query(num);
            ans += min(a, b);
            ans %= mod;
            tree->update(num, 1);
        }
        return ans;
    }
};