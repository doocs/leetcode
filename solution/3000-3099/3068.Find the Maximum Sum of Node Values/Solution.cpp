class Solution {
public:
    long long maximumValueSum(vector<int>& nums, int k, vector<vector<int>>& edges) {
        long long f0 = 0, f1 = -0x3f3f3f3f;
        for (int x : nums) {
            long long tmp = f0;
            f0 = max(f0 + x, f1 + (x ^ k));
            f1 = max(f1 + x, tmp + (x ^ k));
        }
        return f0;
    }
};
