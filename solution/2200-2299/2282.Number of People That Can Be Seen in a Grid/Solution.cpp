class Solution {
public:
    vector<vector<int>> seePeople(vector<vector<int>>& heights) {
        int m = heights.size(), n = heights[0].size();
        auto f = [](vector<int>& nums) {
            int n = nums.size();
            vector<int> ans(n);
            stack<int> stk;
            for (int i = n - 1; ~i; --i) {
                while (stk.size() && stk.top() < nums[i]) {
                    ++ans[i];
                    stk.pop();
                }
                if (stk.size()) {
                    ++ans[i];
                }
                while (stk.size() && stk.top() == nums[i]) {
                    stk.pop();
                }
                stk.push(nums[i]);
            }
            return ans;
        };
        vector<vector<int>> ans;
        for (auto& row : heights) {
            ans.push_back(f(row));
        }
        for (int j = 0; j < n; ++j) {
            vector<int> col;
            for (int i = 0; i < m; ++i) {
                col.push_back(heights[i][j]);
            }
            vector<int> add = f(col);
            for (int i = 0; i < m; ++i) {
                ans[i][j] += add[i];
            }
        }
        return ans;
    }
};