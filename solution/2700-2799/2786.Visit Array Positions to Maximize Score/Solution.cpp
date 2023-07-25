class Solution {
public:
    long long maxScore(vector<int>& nums, int x) {
        const long long inf = 1LL << 60;
        vector<long long> f(2, -inf);
        f[nums[0] & 1] = nums[0];
        int n = nums.size();
        for (int i = 1; i < n; ++i) {
            f[nums[i] & 1] = max(f[nums[i] & 1] + nums[i], f[nums[i] & 1 ^ 1] + nums[i] - x);
        }
        return max(f[0], f[1]);
    }
};