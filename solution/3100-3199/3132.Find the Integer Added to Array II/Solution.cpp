class Solution {
public:
    int minimumAddedInteger(vector<int>& nums1, vector<int>& nums2) {
        sort(nums1.begin(), nums1.end());
        sort(nums2.begin(), nums2.end());
        int ans = 1 << 30;
        auto f = [&](int x) {
            int i = 0, j = 0, cnt = 0;
            while (i < nums1.size() && j < nums2.size()) {
                if (nums2[j] - nums1[i] != x) {
                    ++cnt;
                } else {
                    ++j;
                }
                ++i;
            }
            return cnt <= 2;
        };
        for (int i = 0; i < 3; ++i) {
            int x = nums2[0] - nums1[i];
            if (f(x)) {
                ans = min(ans, x);
            }
        }
        return ans;
    }
};