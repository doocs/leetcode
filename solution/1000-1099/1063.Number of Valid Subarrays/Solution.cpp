class Solution {
public:
    int validSubarrays(vector<int>& nums) {
        int n = nums.size();
        vector<int> right(n, n);
        stack<int> stk;
        for (int i = n - 1; ~i; --i) {
            while (stk.size() && nums[stk.top()] >= nums[i]) {
                stk.pop();
            }
            if (stk.size()) {
                right[i] = stk.top();
            }
            stk.push(i);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += right[i] - i;
        }
        return ans;
    }
};