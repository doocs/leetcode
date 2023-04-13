class BinaryIndexedTree {
public:
    BinaryIndexedTree(int _n)
        : n(_n)
        , c(_n + 1) {}

    void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += x & -x;
        }
    }

    int query(int x) {
        int s = 0;
        while (x) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }

private:
    int n;
    vector<int> c;
};

class Solution {
public:
    int createSortedArray(vector<int>& instructions) {
        int m = *max_element(instructions.begin(), instructions.end());
        BinaryIndexedTree tree(m);
        const int mod = 1e9 + 7;
        int ans = 0;
        for (int i = 0; i < instructions.size(); ++i) {
            int x = instructions[i];
            int cost = min(tree.query(x - 1), i - tree.query(x));
            ans = (ans + cost) % mod;
            tree.update(x, 1);
        }
        return ans;
    }
};