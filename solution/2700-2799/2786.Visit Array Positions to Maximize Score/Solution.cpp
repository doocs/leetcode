class Solution {
public:
    long long maxScore(vector<int>& nums, int x) {
        const long long inf = 1LL << 60;
        vector<long long> f(2, -inf);
        f[nums[0] & 1] = nums[0];
        int n = nums.size();
        for (int i = 1; i < n; ++i) {
            int v = nums[i];
            f[v & 1] = max(f[v & 1], f[v & 1 ^ 1] - x) + v;
        }
        return max(f[0], f[1]);
    }
};