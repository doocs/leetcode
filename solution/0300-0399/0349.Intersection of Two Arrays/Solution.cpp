class Solution {
public:
    vector<int> intersection(vector<int>& nums1, vector<int>& nums2) {
        bool s[1001];
        memset(s, false, sizeof(s));
        for (int x : nums1) {
            s[x] = true;
        }
        vector<int> ans;
        for (int x : nums2) {
            if (s[x]) {
                ans.push_back(x);
                s[x] = false;
            }
        }
        return ans;
    }
};