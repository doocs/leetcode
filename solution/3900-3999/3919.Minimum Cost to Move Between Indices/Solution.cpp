class Solution {
public:
    vector<int> minCost(vector<int>& nums, vector<vector<int>>& queries) {
        int n = nums.size();
        vector<int> s1(n, 0);
        vector<int> s2(n, 0);
        for (int i = 1; i < n; i++) {
            int c1 = (i > 1 && nums[i - 1] - nums[i - 2] <= nums[i] - nums[i - 1]) ? nums[i] - nums[i - 1] : 1;
            int c2 = (i < n - 1 && nums[i] - nums[i - 1] > nums[i + 1] - nums[i]) ? nums[i] - nums[i - 1] : 1;
            s1[i] = s1[i - 1] + c1;
            s2[i] = s2[i - 1] + c2;
        }
        int m = queries.size();
        vector<int> ans(m);
        for (int i = 0; i < m; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            ans[i] = (l < r) ? s1[r] - s1[l] : s2[l] - s2[r];
        }
        return ans;
    }
};
