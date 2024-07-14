class BinaryIndexedTree {
private:
    int n;
    vector<int> c;

public:
    BinaryIndexedTree(int n)
        : n(n)
        , c(n + 1) {}

    void update(int x, int v) {
        for (; x <= n; x += x & -x) {
            c[x] = max(c[x], v);
        }
    }

    int query(int x) {
        int ans = 0;
        for (; x > 0; x -= x & -x) {
            ans = max(ans, c[x]);
        }
        return ans;
    }
};

class Solution {
public:
    int minOperations(vector<int>& target, vector<int>& arr) {
        int m = target.size();
        unordered_map<int, int> d;
        for (int i = 0; i < m; ++i) {
            d[target[i]] = i + 1;
        }
        vector<int> nums;
        for (int x : arr) {
            if (d.contains(x)) {
                nums.push_back(d[x]);
            }
        }
        BinaryIndexedTree tree(m);
        int ans = 0;
        for (int x : nums) {
            int v = tree.query(x - 1) + 1;
            ans = max(ans, v);
            tree.update(x, v);
        }
        return m - ans;
    }
};