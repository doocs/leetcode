class Solution {
public:
    vector<int> canSeePersonsCount(vector<int>& heights) {
        int n = heights.size();
        vector<int> ans(n);
        stack<int> stk;
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.empty()) {
                ans[i]++;
                if (heights[i] <= stk.top()) break;
                stk.pop();
            }
            stk.push(heights[i]);
        }
        return ans;
    }
};
