class Solution {
public:
    int minOperations(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        auto f = [&](int x, int y) {
            int cnt = 0;
            for (int i = 0; i < n - 1; ++i) {
                if (nums1[i] <= x && nums2[i] <= y) {
                    continue;
                }
                if (!(nums1[i] <= y && nums2[i] <= x)) {
                    return -1;
                }
                ++cnt;
            }
            return cnt;
        };
        int a = f(nums1.back(), nums2.back());
        int b = f(nums2.back(), nums1.back());
        return a + b == -2 ? -1 : min(a, b + 1);
    }
};