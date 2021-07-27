class Solution {
public:
    vector<string> summaryRanges(vector<int>& nums) {
        int n = nums.size();
        if (n == 0) {
            return {};
        }
        vector<string> res;
        for (int i = 0, j = 0; j < n;) {
            while (j + 1 < n && nums[j] + 1 == nums[j + 1]) ++j;
            res.push_back(make(nums, i, j));
            i = j + 1;
            j = i;
        }
        return res;
    }

    string make(vector<int>& nums, int i, int j) {
        return i == j ? to_string(nums[i]) : to_string(nums[i]) + "->" + to_string(nums[j]);
    }
};