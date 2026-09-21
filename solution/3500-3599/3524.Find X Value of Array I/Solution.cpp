class Solution {
public:
    vector<long long> resultArray(vector<int>& nums, int k) {
        vector<long long> ans(k);
        vector<long long> f(k);
        for (int x : nums) {
            vector<long long> g(k);
            for (int r = 0; r < k; ++r) {
                g[1LL * r * x % k] += f[r];
            }
            g[x % k] += 1;
            for (int r = 0; r < k; ++r) {
                ans[r] += g[r];
            }
            f.swap(g);
        }
        return ans;
    }
};
