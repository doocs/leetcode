class Solution {
public:
    int pivotIndex(vector<int>& nums) {
        int sum = 0;
        int total = 0;
        for (int num : nums)
            sum += num;

        for (int i = 0; i < nums.size(); i++) {
            total += nums[i];
            if (total - nums[i] == sum - total)
                return i;
        }

        return -1;
    }
};