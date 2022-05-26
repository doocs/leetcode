class Solution {
public:
    vector<int> intersection(vector<vector<int>>& nums) {
        vector<int> cnt(1001);
        for (auto& num : nums)
            for (int v : num)
                ++cnt[v];
        int n = nums.size();
        vector<int> ans;
        for (int i = 1; i < 1001; ++i)
            if (cnt[i] == n)
                ans.push_back(i);
        return ans;
    }
};