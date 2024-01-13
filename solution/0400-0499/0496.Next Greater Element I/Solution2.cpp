class Solution {
public:
    vector<int> nextGreaterElement(vector<int>& nums1, vector<int>& nums2) {
        stack<int> stk;
        unordered_map<int, int> m;
        for (int i = nums2.size() - 1; ~i; --i) {
            while (!stk.empty() && stk.top() <= nums2[i]) stk.pop();
            if (!stk.empty()) m[nums2[i]] = stk.top();
            stk.push(nums2[i]);
        }
        vector<int> ans;
        for (int& v : nums1) ans.push_back(m.count(v) ? m[v] : -1);
        return ans;
    }
};