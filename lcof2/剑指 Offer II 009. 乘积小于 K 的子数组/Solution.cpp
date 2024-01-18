class Solution {
public:
    int numSubarrayProductLessThanK(vector<int>& nums, int k) {
        long long s = 1;
        int ans = 0, n = nums.size();
        for (int i = 0, j = 0; j < n; ++j) {
            s *= nums[j];
            while (i <= j && s >= k) {
                s /= nums[i++];
            }
            ans += j - i + 1;
        }
        return ans;
    }
};