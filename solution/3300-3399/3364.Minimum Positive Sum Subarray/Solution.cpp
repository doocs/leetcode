class Solution {
public:
    int minimumSumSubarray(vector<int>& nums, int l, int r) {
        int n = nums.size();
        const int inf = INT_MAX;
        int ans = inf;
        for (int i = 0; i < n; ++i) {
            int s = 0;
            for (int j = i; j < n; ++j) {
                s += nums[j];
                int k = j - i + 1;
                if (k >= l && k <= r && s > 0) {
                    ans = min(ans, s);
                }
            }
        }
        return ans == inf ? -1 : ans;
    }
};
