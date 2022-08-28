class Solution {
public:
    vector<int> answerQueries(vector<int>& nums, vector<int>& queries) {
        sort(nums.begin(), nums.end());
        int n = nums.size(), m = queries.size();
        vector<int> s(n + 1);
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        vector<int> ans(m);
        for (int i = 0; i < m; ++i) {
            ans[i] = upper_bound(s.begin() + 1, s.end(), queries[i]) - s.begin() - 1;
        }
        return ans;
    }
};