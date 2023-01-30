class Solution {
public:
    vector<int> intersection(vector<vector<int>>& nums) {
        int cnt[1001]{};
        for (auto& arr : nums) {
            for (int& x : arr) {
                ++cnt[x];
            }
        }
        vector<int> ans;
        for (int x = 0; x < 1001; ++x) {
            if (cnt[x] == nums.size()) {
                ans.push_back(x);
            }
        }
        return ans;
    }
};