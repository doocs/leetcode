class Solution {
public:
    int minOperations(vector<int>& nums1, vector<int>& nums2) {
        int s1 = accumulate(nums1.begin(), nums1.end(), 0);
        int s2 = accumulate(nums2.begin(), nums2.end(), 0);
        if (s1 == s2) return 0;
        if (s1 > s2) return minOperations(nums2, nums1);
        int d = s2 - s1;
        int cnt[6] = {0};
        for (int& v : nums1) ++cnt[6 - v];
        for (int& v : nums2) ++cnt[v - 1];
        int ans = 0;
        for (int i = 5; i; --i) {
            while (cnt[i] && d > 0) {
                d -= i;
                --cnt[i];
                ++ans;
            }
        }
        return d <= 0 ? ans : -1;
    }
};