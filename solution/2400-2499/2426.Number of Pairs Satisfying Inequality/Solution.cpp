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
    long long numberOfPairs(vector<int>& nums1, vector<int>& nums2, int diff) {
        BinaryIndexedTree* tree = new BinaryIndexedTree(1e5);
        long long ans = 0;
        for (int i = 0; i < nums1.size(); ++i) {
            int v = nums1[i] - nums2[i];
            ans += tree->query(v + diff + 40000);
            tree->update(v + 40000, 1);
        }
        return ans;
    }
};