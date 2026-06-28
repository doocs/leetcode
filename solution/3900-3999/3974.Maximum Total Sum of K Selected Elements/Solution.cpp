class Solution {
public:
    long long maxSum(vector<int>& nums, int k, int mul) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        long long ans = 0;

        for (int i = n - 1; i >= n - k; --i) {
            int m = max(1, mul);
            ans += 1LL * nums[i] * m;
            mul--;
        }

        return ans;
    }
};