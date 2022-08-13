class Solution {
public:
    vector<int> advantageCount(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        vector<pair<int, int>> t;
        for (int i = 0; i < n; ++i) t.push_back({nums2[i], i});
        sort(t.begin(), t.end());
        sort(nums1.begin(), nums1.end());
        int i = 0, j = n - 1;
        vector<int> ans(n);
        for (int v : nums1) {
            if (v <= t[i].first)
                ans[t[j--].second] = v;
            else
                ans[t[i++].second] = v;
        }
        return ans;
    }
};