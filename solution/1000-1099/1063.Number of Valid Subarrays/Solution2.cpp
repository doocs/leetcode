class Solution {
public:
    int validSubarrays(vector<int>& nums) {
        int n = nums.size();
        stack<int> stk;
        int ans = 0;
        for (int i = n - 1; ~i; --i) {
            while (stk.size() && nums[stk.top()] >= nums[i]) {
                stk.pop();
            }
            ans += (stk.size() ? stk.top() : n) - i;
            stk.push(i);
        }
        return ans;
    }
};