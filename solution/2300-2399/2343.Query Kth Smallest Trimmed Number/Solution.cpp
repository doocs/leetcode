class Solution {
public:
    vector<int> smallestTrimmedNumbers(vector<string>& nums, vector<vector<int>>& queries) {
        int n = nums.size();
        vector<pair<string, int>> t(n);
        vector<int> ans;
        for (auto& q : queries) {
            int k = q[0], trim = q[1];
            for (int j = 0; j < n; ++j) {
                t[j] = {nums[j].substr(nums[j].size() - trim), j};
            }
            sort(t.begin(), t.end());
            ans.push_back(t[k - 1].second);
        }
        return ans;
    }
};