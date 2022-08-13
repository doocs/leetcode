class Solution {
public:
    int minOperations(vector<int>& nums, int x) {
        int n = nums.size();
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        int target = sum - x;
        int res = -1;
        int l = 0;
        int r = 0;
        sum = 0;
        while (r < n) {
            sum += nums[r++];
            while (sum > target && l < n) {
                sum -= nums[l++];
            }
            if (sum == target) {
                res = max(res, r - l);
            }
        }

        if (res == -1) {
            return res;
        }
        return n - res;
    }
};