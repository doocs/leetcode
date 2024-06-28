class Solution {
public:
    vector<int> intersect(vector<int>& nums1, vector<int>& nums2) {
        unordered_map<int, int> cnt;
        for (int x : nums1) {
            ++cnt[x];
        }
        vector<int> ans;
        for (int x : nums2) {
            if (cnt[x]-- > 0) {
                ans.push_back(x);
            }
        }
        return ans;
    }
};