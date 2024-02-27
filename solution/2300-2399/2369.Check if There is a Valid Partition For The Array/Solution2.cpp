class Solution {
public:
    bool validPartition(vector<int>& nums) {
        int n = nums.size();
        vector<bool> f(n + 1);
        f[0] = true;
        for (int i = 1; i <= n; ++i) {
            bool a = i - 2 >= 0 && nums[i - 1] == nums[i - 2];
            bool b = i - 3 >= 0 && nums[i - 1] == nums[i - 2] && nums[i - 2] == nums[i - 3];
            bool c = i - 3 >= 0 && nums[i - 1] - nums[i - 2] == 1 && nums[i - 2] - nums[i - 3] == 1;
            f[i] = (a && f[i - 2]) || ((b || c) && f[i - 3]);
        }
        return f[n];
    }
};