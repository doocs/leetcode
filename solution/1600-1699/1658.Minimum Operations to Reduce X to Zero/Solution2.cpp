class Solution {
public:
    int minOperations(vector<int>& nums, int x) {
        int s = accumulate(nums.begin(), nums.end(), 0) - x;
        int mx = -1, t = 0;
        int n = nums.size();
        for (int i = 0, j = 0; i < n; ++i) {
            t += nums[i];
            while (j <= i && t > s) {
                t -= nums[j++];
            }
            if (t == s) {
                mx = max(mx, i - j + 1);
            }
        }
        return mx == -1 ? -1 : n - mx;
    }
};