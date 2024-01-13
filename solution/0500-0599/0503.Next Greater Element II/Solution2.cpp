class Solution {
public:
    vector<int> nextGreaterElements(vector<int>& nums) {
        int n = nums.size();
        vector<int> ans(n, -1);
        stack<int> stk;
        for (int i = n * 2 - 1; ~i; --i) {
            int j = i % n;
            while (!stk.empty() && stk.top() <= nums[j]) stk.pop();
            if (!stk.empty()) ans[j] = stk.top();
            stk.push(nums[j]);
        }
        return ans;
    }
};