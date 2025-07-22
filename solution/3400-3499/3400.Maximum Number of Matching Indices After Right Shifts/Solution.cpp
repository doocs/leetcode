class Solution {
public:
    int maximumMatchingIndices(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        int ans = 0;
        for (int k = 0; k < n; ++k) {
            int t = 0;
            for (int i = 0; i < n; ++i) {
                if (nums1[(i + k) % n] == nums2[i]) {
                    ++t;
                }
            }
            ans = max(ans, t);
        }
        return ans;
    }
};
