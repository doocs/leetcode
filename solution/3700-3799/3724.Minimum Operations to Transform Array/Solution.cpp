class Solution {
public:
    long long minOperations(vector<int>& nums1, vector<int>& nums2) {
        long long ans = 1;
        int n = nums1.size();
        bool ok = false;
        int d = 1 << 30;
        for (int i = 0; i < n; ++i) {
            int x = max(nums1[i], nums2[i]);
            int y = min(nums1[i], nums2[i]);
            ans += x - y;
            d = min(d, min(abs(x - nums2[n]), abs(y - nums2[n])));
            ok = ok || (nums2[n] >= y && nums2[n] <= x);
        }
        if (!ok) {
            ans += d;
        }
        return ans;
    }
};
