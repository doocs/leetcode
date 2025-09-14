class Solution {
public:
    int minimumSum(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        const int inf = INT_MAX;
        unordered_map<int, int> d;
        for (int i = 0; i < n; i++) {
            if (!d.contains(nums2[i])) {
                d[nums2[i]] = i;
            }
        }
        int ans = inf;
        for (int i = 0; i < n; i++) {
            if (d.contains(nums1[i])) {
                ans = min(ans, i + d[nums1[i]]);
            }
        }
        return ans == inf ? -1 : ans;
    }
};
