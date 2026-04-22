struct BIT {
    int n;
    vector<int> c;
    BIT(int n)
        : n(n)
        , c(n + 1, 0) {}
    void update(int x, int delta) {
        for (; x <= n; x += x & -x) c[x] += delta;
    }
    int query(int x) {
        int s = 0;
        for (; x > 0; x -= x & -x) s += c[x];
        return s;
    }
};

class Solution {
public:
    vector<int> countSmallerOppositeParity(vector<int>& nums) {
        int n = nums.size();
        vector<int> sorted = nums;
        sort(sorted.begin(), sorted.end());
        sorted.erase(unique(sorted.begin(), sorted.end()), sorted.end());

        int m = sorted.size();
        BIT* bits[2] = {new BIT(m), new BIT(m)};
        vector<int> ans(n);

        for (int i = n - 1; i >= 0; --i) {
            int x = lower_bound(sorted.begin(), sorted.end(), nums[i]) - sorted.begin() + 1;
            ans[i] = bits[nums[i] & 1 ^ 1]->query(x - 1);
            bits[nums[i] & 1]->update(x, 1);
        }
        return ans;
    }
};
