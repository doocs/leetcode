class Solution {
public:
    vector<int> arrayChange(vector<int>& nums, vector<vector<int>>& operations) {
        int n = nums.size();
        unordered_map<int, int> d;
        for (int i = 0; i < n; ++i) d[nums[i]] = i;
        for (auto& op : operations) {
            int a = op[0], b = op[1];
            int idx = d[a];
            d.erase(a);
            d[b] = idx;
        }
        vector<int> ans(n);
        for (auto& [v, i] : d) ans[i] = v;
        return ans;
    }
};