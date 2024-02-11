class Solution {
public:
    int countMatchingSubarrays(vector<int>& nums, vector<int>& pattern) {
        assert(nums.size() >= 2 && nums.size() <= 100);
        for (int x : nums) {
            assert(x >= 1 && x <= 1000000000);
        }
        const int m = pattern.size();
        assert(m >= 1 && m < nums.size());
        for (int x : pattern) {
            assert(abs(x) <= 1);
        }
        int r = 0;
        for (int i = 0; i + m < nums.size(); ++i) {
            bool mark = true;
            for (int k = 0; mark && k < m; ++k) {
                mark = (pattern[k] == 1 && nums[i + k + 1] > nums[i + k])
                    || (pattern[k] == 0 && nums[i + k + 1] == nums[i + k])
                    || (pattern[k] == -1 && nums[i + k + 1] < nums[i + k]); 
            }
            r += mark;
        }
        return r;
    }
};
