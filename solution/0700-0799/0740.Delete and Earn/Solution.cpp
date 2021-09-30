class Solution {
public:
    int deleteAndEarn(vector<int>& nums) {
        vector<int> vals(10010);
        for (int& num : nums) {
            vals[num] += num;
        }
        return rob(vals);
    }

    int rob(vector<int>& nums) {
        int a = 0, b = nums[0];
        for (int i = 1; i < nums.size(); ++i) {
            int c = max(nums[i] + a, b);
            a = b;
            b = c;
        }
        return b;
    }
};