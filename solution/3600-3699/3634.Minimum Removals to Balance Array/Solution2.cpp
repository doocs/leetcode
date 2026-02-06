class Solution {
public:
    int minRemoval(vector<int>& nums, int k) {
        ranges::sort(nums);
        int n = nums.size();
        int ans = n;
        int r = 0;
        for (int l = 0; l < n; l++) {
            while (r < n && nums[r] <= (long long) nums[l] * k) {
                r++;
            }
            ans = min(ans, n - (r - l));
        }
        return ans;
    }
};
