class BinaryIndexedTree {
public:
    explicit BinaryIndexedTree(int n)
        : n(n)
        , c(n + 1) {}

    void update(int x, long long val) {
        while (x <= n) {
            c[x] = max(c[x], val);
            x += x & -x;
        }
    }

    long long query(int x) {
        long long ans = 0;
        while (x > 0) {
            ans = max(ans, c[x]);
            x -= x & -x;
        }
        return ans;
    }

private:
    int n;
    vector<long long> c;
};

class Solution {
public:
    long long maxAlternatingSum(vector<int>& nums, int k) {
        vector<int> sorted = nums;
        ranges::sort(sorted);
        sorted.erase(unique(sorted.begin(), sorted.end()), sorted.end());
        int m = sorted.size();
        auto rank = [&](int x) {
            return ranges::lower_bound(sorted, x) - sorted.begin() + 1;
        };
        BinaryIndexedTree bit0(m), bit1(m);
        int n = nums.size();
        vector<array<long long, 2>> f(n);
        long long ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i >= k) {
                int r = rank(nums[i - k]);
                bit0.update(r, f[i - k][0]);
                bit1.update(m + 1 - r, f[i - k][1]);
            }
            int r = rank(nums[i]);
            f[i][0] = nums[i] + bit1.query(m - r);
            f[i][1] = nums[i] + bit0.query(r - 1);
            ans = max({ans, f[i][0], f[i][1]});
        }
        return ans;
    }
};
