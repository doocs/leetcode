class Solution {
public:
    vector<int> canSeePersonsCount(vector<int>& heights) {
        int n = heights.size();
        vector<int> ans(n);
        stack<int> stk;
        for (int i = n - 1; ~i; --i) {
            while (stk.size() && stk.top() < heights[i]) {
                ++ans[i];
                stk.pop();
            }
            if (stk.size()) {
                ++ans[i];
            }
            stk.push(heights[i]);
        }
        return ans;
    }
};