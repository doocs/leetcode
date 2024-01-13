class BinaryIndexedTree {
private:
    int n;
    vector<long long> c;
    const long long inf = 1e18;

public:
    BinaryIndexedTree(int n) {
        this->n = n;
        c.resize(n + 1, -inf);
    }

    void update(int x, long long v) {
        while (x <= n) {
            c[x] = max(c[x], v);
            x += x & -x;
        }
    }

    long long query(int x) {
        long long mx = -inf;
        while (x > 0) {
            mx = max(mx, c[x]);
            x -= x & -x;
        }
        return mx;
    }
};

class Solution {
public:
    long long maxBalancedSubsequenceSum(vector<int>& nums) {
        int n = nums.size();
        vector<int> arr(n);
        for (int i = 0; i < n; ++i) {
            arr[i] = nums[i] - i;
        }
        sort(arr.begin(), arr.end());
        arr.erase(unique(arr.begin(), arr.end()), arr.end());
        int m = arr.size();
        BinaryIndexedTree tree(m);
        for (int i = 0; i < n; ++i) {
            int j = lower_bound(arr.begin(), arr.end(), nums[i] - i) - arr.begin() + 1;
            long long v = max(tree.query(j), 0LL) + nums[i];
            tree.update(j, v);
        }
        return tree.query(m);
    }
};