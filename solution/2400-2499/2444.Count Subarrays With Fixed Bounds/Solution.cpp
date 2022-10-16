class Solution {
public:
    long long countSubarrays(vector<int>& nums, int minK, int maxK) {
        long long ans = 0;
        int j1 = -1, j2 = -1, k = -1;
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] < minK || nums[i] > maxK) k = i;
            if (nums[i] == minK) j1 = i;
            if (nums[i] == maxK) j2 = i;
            ans += max(0, min(j1, j2) - k);
        }
        return ans;
    }
};