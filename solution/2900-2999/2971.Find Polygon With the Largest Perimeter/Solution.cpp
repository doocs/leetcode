class Solution {
public:
    long long largestPerimeter(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        vector<long long> s(n + 1);
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + nums[i - 1];
        }
        long long ans = -1;
        for (int k = 3; k <= n; ++k) {
            if (s[k - 1] > nums[k - 1]) {
                ans = max(ans, s[k]);
            }
        }
        return ans;
    }
};