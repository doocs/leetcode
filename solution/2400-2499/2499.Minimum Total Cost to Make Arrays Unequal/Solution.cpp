class Solution {
public:
    long long minimumTotalCost(vector<int>& nums1, vector<int>& nums2) {
        long long ans = 0;
        int same = 0;
        int n = nums1.size();
        int cnt[n + 1];
        memset(cnt, 0, sizeof cnt);
        for (int i = 0; i < n; ++i) {
            if (nums1[i] == nums2[i]) {
                ans += i;
                ++same;
                ++cnt[nums1[i]];
            }
        }
        int m = 0, lead = 0;
        for (int i = 0; i < n + 1; ++i) {
            int t = cnt[i] * 2 - same;
            if (t > 0) {
                m = t;
                lead = i;
                break;
            }
        }
        for (int i = 0; i < n; ++i) {
            if (m > 0 && nums1[i] != nums2[i] && nums1[i] != lead && nums2[i] != lead) {
                ans += i;
                --m;
            }
        }
        return m > 0 ? -1 : ans;
    }
};