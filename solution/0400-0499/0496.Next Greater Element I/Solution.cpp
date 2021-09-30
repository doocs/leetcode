class Solution {
public:
    vector<int> nextGreaterElement(vector<int> &nums1, vector<int> &nums2) {
        stack<int> stk;
        unordered_map<int, int> mp;
        for (int i = nums2.size() - 1; i >= 0; --i)
        {
            while (!stk.empty() && stk.top() <= nums2[i])
                stk.pop();
            mp[nums2[i]] = stk.empty() ? -1 : stk.top();
            stk.push(nums2[i]);
        }
        vector<int> res;
        for (int num : nums1)
            res.push_back(mp[num]);
        return res;
    }
};