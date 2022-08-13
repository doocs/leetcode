class Solution {
public:
    vector<int> twoOutOfThree(vector<int>& nums1, vector<int>& nums2, vector<int>& nums3) {
        auto s1 = get(nums1), s2 = get(nums2), s3 = get(nums3);
        vector<int> ans;
        for (int i = 1; i <= 100; ++i)
            if (s1[i] + s2[i] + s3[i] > 1)
                ans.push_back(i);
        return ans;
    }

    vector<int> get(vector<int>& nums) {
        vector<int> s(101);
        for (int num : nums) s[num] = 1;
        return s;
    }
};