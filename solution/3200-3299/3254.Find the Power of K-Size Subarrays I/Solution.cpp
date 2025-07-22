class Solution {
public:
    vector<int> resultsArray(vector<int>& nums, int k) {
        int n = nums.size();
        int f[n];
        f[0] = 1;
        for (int i = 1; i < n; ++i) {
            f[i] = nums[i] == nums[i - 1] + 1 ? f[i - 1] + 1 : 1;
        }
        vector<int> ans;
        for (int i = k - 1; i < n; ++i) {
            ans.push_back(f[i] >= k ? nums[i] : -1);
        }
        return ans;
    }
};
