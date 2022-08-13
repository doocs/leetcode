class Solution {
public:
    vector<int> nextGreaterElement(vector<int>& nums1, vector<int>& nums2) {
        stack<int> stk;
        unordered_map<int, int> m;
        for (int& v : nums2) {
            while (!stk.empty() && stk.top() < v) {
                m[stk.top()] = v;
                stk.pop();
            }
            stk.push(v);
        }
        vector<int> ans;
        for (int& v : nums1) ans.push_back(m.count(v) ? m[v] : -1);
        return ans;
    }
};