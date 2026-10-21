class Solution {
public:
    long long maxSum(vector<int>& nums, int length, int k) {
        int n = nums.size();
        int maxSwaps = min(k, min(length, n - length));
        long long ans = LLONG_MIN;
        for (int i = 0; i <= n - length; ++i) {
            vector<int> internal(nums.begin() + i, nums.begin() + i + length);
            vector<int> external(nums.begin(), nums.begin() + i);
            external.insert(external.end(), nums.begin() + i + length, nums.end());
            sort(internal.begin(), internal.end());
            sort(external.rbegin(), external.rend());
            long long total = accumulate(internal.begin(), internal.end(), 0LL);
            for (int j = 0; j < maxSwaps; ++j) {
                long long gain = (long long) external[j] - internal[j];
                if (gain <= 0) {
                    break;
                }
                total += gain;
            }
            ans = max(ans, total);
        }
        return ans;
    }
};
