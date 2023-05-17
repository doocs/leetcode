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
    long long countOperationsToEmptyArray(vector<int>& nums) {
        unordered_map<int, int> pos;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            pos[nums[i]] = i;
        }
        sort(nums.begin(), nums.end());
        BinaryIndexedTree tree(n);
        long long ans = pos[nums[0]] + 1;
        for (int k = 0; k < n - 1; ++k) {
            int i = pos[nums[k]], j = pos[nums[k + 1]];
            long long d = j - i - (tree.query(j + 1) - tree.query(i + 1));
            ans += d + (n - k) * int(i > j);
            tree.update(i + 1, 1);
        }
        return ans;
    }
};