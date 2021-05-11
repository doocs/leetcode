class Solution {
public:
    int maxDistance(vector<int>& nums1, vector<int>& nums2) {
        int res = 0;
        for (int i = 0; i < nums1.size(); ++i) {
            int l = i, r = nums2.size() - 1;
            while (l <= r) {
                int mid = (l + r) >> 1;
                if (nums2[mid] >= nums1[i]) {
                    res = max(res, mid - i);
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return res;
    }
};