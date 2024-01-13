class Solution {
public:
    int minimumSeconds(vector<int>& nums) {
        unordered_map<int, vector<int>> d;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            d[nums[i]].push_back(i);
        }
        int ans = 1 << 30;
        for (auto& [_, idx] : d) {
            int m = idx.size();
            int t = idx[0] + n - idx[m - 1];
            for (int i = 1; i < m; ++i) {
                t = max(t, idx[i] - idx[i - 1]);
            }
            ans = min(ans, t / 2);
        }
        return ans;
    }
};