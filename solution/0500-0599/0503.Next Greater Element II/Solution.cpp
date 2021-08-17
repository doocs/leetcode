class Solution {
public:
    vector<int> nextGreaterElements(vector<int> &nums) {
        int n = nums.size();
        vector<int> res(n, -1);
        stack<int> stk;
        for (int i = 0; i < (n << 1); ++i)
        {
            while (!stk.empty() && nums[stk.top()] < nums[i % n])
            {
                res[stk.top()] = nums[i % n];
                stk.pop();
            }
            stk.push(i % n);
        }
        return res;
    }
};