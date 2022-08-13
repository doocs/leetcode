class Solution {
public:
    long long countPairs(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        vector<int> d(n);
        for (int i = 0; i < n; ++i) d[i] = nums1[i] - nums2[i];
        sort(d.begin(), d.end());
        long long ans = 0;
        for (int i = 0; i < n; ++i) {
            int j = upper_bound(d.begin() + i + 1, d.end(), -d[i]) - d.begin();
            ans += n - j;
        }
        return ans;
    }
};