class Solution {
public:
    int maxValueAfterReverse(vector<int>& nums) {
        int n = nums.size();
        int s = 0;
        for (int i = 0; i < n - 1; ++i) {
            s += abs(nums[i] - nums[i + 1]);
        }
        int ans = s;
        for (int i = 0; i < n - 1; ++i) {
            ans = max(ans, s + abs(nums[0] - nums[i + 1]) - abs(nums[i] - nums[i + 1]));
            ans = max(ans, s + abs(nums[n - 1] - nums[i]) - abs(nums[i] - nums[i + 1]));
        }
        int dirs[5] = {1, -1, -1, 1, 1};
        const int inf = 1 << 30;
        for (int k = 0; k < 4; ++k) {
            int k1 = dirs[k], k2 = dirs[k + 1];
            int mx = -inf, mi = inf;
            for (int i = 0; i < n - 1; ++i) {
                int a = k1 * nums[i] + k2 * nums[i + 1];
                int b = abs(nums[i] - nums[i + 1]);
                mx = max(mx, a - b);
                mi = min(mi, a + b);
            }
            ans = max(ans, s + max(0, mx - mi));
        }
        return ans;
    }
};