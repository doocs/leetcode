class Solution {
public:
    vector<int> findIntersectionValues(vector<int>& nums1, vector<int>& nums2) {
        int s1[101]{};
        int s2[101]{};
        for (int& x : nums1) {
            s1[x] = 1;
        }
        for (int& x : nums2) {
            s2[x] = 1;
        }
        vector<int> ans(2);
        for (int& x : nums1) {
            ans[0] += s2[x];
        }
        for (int& x : nums2) {
            ans[1] += s1[x];
        }
        return ans;
    }
};