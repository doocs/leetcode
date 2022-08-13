class Solution {
public:
    int minOperations(vector<int>& nums1, vector<int>& nums2) {
        int s1 = accumulate(nums1.begin(), nums1.end(), 0);
        int s2 = accumulate(nums2.begin(), nums2.end(), 0);
        if (s1 == s2) return 0;
        if (s1 > s2) return minOperations(nums2, nums1);
        vector<int> freq(6);
        for (int x : nums1) ++freq[6 - x];
        for (int x : nums2) ++freq[x - 1];
        int diff = s2 - s1;
        int ans = 0;
        for (int i = 5; i > 0 && diff > 0; --i) {
            while (freq[i] && diff > 0) {
                diff -= i;
                --freq[i];
                ++ans;
            }
        }
        return diff > 0 ? -1 : ans;
    }
};