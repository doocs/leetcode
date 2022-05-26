class Solution {
public:
    vector<int> maxSubsequence(vector<int>& nums, int k) {
        int n = nums.size();
        vector<pair<int, int>> vals;
        for (int i = 0; i < n; ++i) vals.push_back({i, nums[i]});
        sort(vals.begin(), vals.end(), [&](auto x1, auto x2) {
            return x1.second > x2.second;
        });
        sort(vals.begin(), vals.begin() + k);
        vector<int> ans;
        for (int i = 0; i < k; ++i) ans.push_back(vals[i].second);
        return ans;
    }
};