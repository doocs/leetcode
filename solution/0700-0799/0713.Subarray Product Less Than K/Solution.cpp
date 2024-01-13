class Solution {
public:
    int numSubarrayProductLessThanK(vector<int>& nums, int k) {
        int ans = 0;
        for (int i = 0, j = 0, s = 1; i < nums.size(); ++i) {
            s *= nums[i];
            while (j <= i && s >= k) s /= nums[j++];
            ans += i - j + 1;
        }
        return ans;
    }
};