class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        for (int p = 0, q = nums.size() - 1; p < q;) {
            int s = nums[p] + nums[q];
            if (s == target) {
                return vector<int> {nums[p], nums[q]};
            }
            if (s < target) {
                ++p;
            } else {
                --q;
            }
        }
        return vector<int> {};
    }
};