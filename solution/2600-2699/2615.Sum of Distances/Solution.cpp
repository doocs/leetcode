class Solution {
public:
    vector<long long> distance(vector<int>& nums) {
        int n = nums.size();
        vector<long long> ans(n);
        unordered_map<int, vector<int>> d;
        for (int i = 0; i < n; ++i) {
            d[nums[i]].push_back(i);
        }
        for (auto& [_, idx] : d) {
            int m = idx.size();
            long long left = 0;
            long long right = -1LL * m * idx[0];
            for (int i : idx) {
                right += i;
            }
            for (int i = 0; i < m; ++i) {
                ans[idx[i]] = left + right;
                if (i + 1 < m) {
                    left += (idx[i + 1] - idx[i]) * (i + 1);
                    right -= (idx[i + 1] - idx[i]) * (m - i - 1);
                }
            }
        }
        return ans;
    }
};