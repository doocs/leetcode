class Solution {
public:
    long long countSubarrays(vector<int>& nums, int minK, int maxK) {
        long long ans = 0;
        int l = 0;
        for (int r = 0; r < nums.size(); r++) {
            int x = nums[r];
            if (x < minK || x > maxK) {
                l = r + 1;
            } else {
                ans += r - l + 1;
            }
        }
        return ans;
    }
};