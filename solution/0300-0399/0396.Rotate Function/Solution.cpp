class Solution {
public:
    int maxRotateFunction(vector<int>& nums) {
        long long f = 0, s = 0;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            f += 1LL * i * nums[i];
            s += nums[i];
        }
        long long ans = f;
        for (int i = 1; i < n; ++i) {
            f = f + s - 1LL * n * nums[n - i];
            ans = max(ans, f);
        }
        return (int) ans;
    }
};