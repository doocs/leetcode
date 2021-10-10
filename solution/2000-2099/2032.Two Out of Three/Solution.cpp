class Solution {
public:
    vector<int> twoOutOfThree(vector<int>& nums1, vector<int>& nums2, vector<int>& nums3) {
        auto s1 = get(nums1), s2 = get(nums2), s3 = get(nums3);
        vector<int> ans;
        for (int i = 1; i <= 100; ++i)
        {
            int a = s1.count(i) ? 1 : 0;
            int b = s2.count(i) ? 1 : 0;
            int c = s3.count(i) ? 1 : 0;
            if (a + b + c > 1) ans.push_back(i);
        }
        return ans;
    }

    unordered_set<int> get(vector<int>& nums) {
        unordered_set<int> s;
        for (int num : nums) s.insert(num);
        return s;
    }
};