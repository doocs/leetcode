class BinaryIndexedTree {
public:
    BinaryIndexedTree(int _n)
        : n(_n)
        , c(_n + 1) {}

    void update(int x, int v) {
        while (x <= n) {
            c[x] += v;
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
    int countRangeSum(vector<int>& nums, int lower, int upper) {
        using ll = long long;
        int n = nums.size();
        ll s[n + 1];
        s[0] = 0;
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        ll arr[(n + 1) * 3];
        for (int i = 0, j = 0; i <= n; ++i, j += 3) {
            arr[j] = s[i];
            arr[j + 1] = s[i] - lower;
            arr[j + 2] = s[i] - upper;
        }
        sort(arr, arr + (n + 1) * 3);
        int m = unique(arr, arr + (n + 1) * 3) - arr;
        BinaryIndexedTree tree(m);
        int ans = 0;
        for (int i = 0; i <= n; ++i) {
            int l = lower_bound(arr, arr + m, s[i] - upper) - arr + 1;
            int r = lower_bound(arr, arr + m, s[i] - lower) - arr + 1;
            ans += tree.query(r) - tree.query(l - 1);
            tree.update(lower_bound(arr, arr + m, s[i]) - arr + 1, 1);
        }
        return ans;
    }
};