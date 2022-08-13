class Solution {
public:
    int pivotIndex(vector<int>& nums) {
        int s = 0;
        for (int e : nums)
            s += e;
        int presum = 0;
        for (int i = 0; i < nums.size(); ++i) {
            if (presum * 2 == s - nums[i])
                return i;
            presum += nums[i];
        }
        return -1;
    }
};