class Solution {
public:
    vector<int> anagramMappings(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        unordered_map<int, int> d;
        for (int i = 0; i < n; ++i) {
            d[nums2[i]] = i;
        }
        vector<int> ans;
        for (int x : nums1) {
            ans.push_back(d[x]);
        }
        return ans;
    }
};
