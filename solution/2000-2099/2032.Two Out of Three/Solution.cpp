class Solution {
public:
    vector<int> twoOutOfThree(vector<int>& nums1, vector<int>& nums2, vector<int>& nums3) {
        auto get = [](vector<int>& nums) {
            vector<int> cnt(101);
            for (int& v : nums) cnt[v] = 1;
            return cnt;
        };
        auto s1 = get(nums1), s2 = get(nums2), s3 = get(nums3);
        vector<int> ans;
        for (int i = 1; i <= 100; ++i) {
            if (s1[i] + s2[i] + s3[i] > 1) {
                ans.emplace_back(i);
            }
        }
        return ans;
    }
};