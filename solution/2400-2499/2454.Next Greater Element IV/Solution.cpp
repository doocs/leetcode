using pii = pair<int, int>;

class Solution {
public:
    vector<int> secondGreaterElement(vector<int>& nums) {
        stack<int> stk;
        priority_queue<pii, vector<pii>, greater<pii>> q;
        int n = nums.size();
        vector<int> ans(n, -1);
        for (int i = 0; i < n; ++i) {
            int v = nums[i];
            while (!q.empty() && q.top().first < v) {
                ans[q.top().second] = v;
                q.pop();
            }
            while (!stk.empty() && nums[stk.top()] < v) {
                q.push({nums[stk.top()], stk.top()});
                stk.pop();
            }
            stk.push(i);
        }
        return ans;
    }
};