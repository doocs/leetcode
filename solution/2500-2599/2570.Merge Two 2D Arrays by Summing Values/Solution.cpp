class Solution {
public:
    vector<vector<int>> mergeArrays(vector<vector<int>>& nums1, vector<vector<int>>& nums2) {
        int cnt[1001]{};
        for (auto& x : nums1) {
            cnt[x[0]] += x[1];
        }
        for (auto& x : nums2) {
            cnt[x[0]] += x[1];
        }
        vector<vector<int>> ans;
        for (int i = 0; i < 1001; ++i) {
            if (cnt[i]) {
                ans.push_back({i, cnt[i]});
            }
        }
        return ans;
    }
};