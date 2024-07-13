class Solution {
public:
    int wiggleMaxLength(vector<int>& nums) {
        int n = nums.size();
        int ans = 1;
        vector<int> f(n, 1);
        vector<int> g(n, 1);
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    f[i] = max(f[i], g[j] + 1);
                } else if (nums[j] > nums[i]) {
                    g[i] = max(g[i], f[j] + 1);
                }
            }
            ans = max({ans, f[i], g[i]});
        }
        return ans;
    }
};