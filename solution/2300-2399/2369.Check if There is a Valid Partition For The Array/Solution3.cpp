class Solution {
public:
    bool validPartition(vector<int>& nums) {
        int n = nums.size();
        vector<int> f(n + 1);
        f[n] = 1;
        for (int i = n - 1; i >= 0; --i) {
            bool a = i + 1 < n && nums[i] == nums[i + 1];
            bool b = i + 2 < n && nums[i] == nums[i + 1] && nums[i + 1] == nums[i + 2];
            bool c = i + 2 < n && nums[i + 1] - nums[i] == 1 && nums[i + 2] - nums[i + 1] == 1;
            f[i] = (a && f[i + 2]) || ((b || c) && f[i + 3]);
        }
        return f[0];
    }
};
