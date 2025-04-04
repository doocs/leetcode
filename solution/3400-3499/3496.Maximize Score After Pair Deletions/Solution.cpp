class Solution {
public:
    int maxScore(vector<int>& nums) {
        const int inf = 1 << 30;
        int n = nums.size();
        int s = 0, mi = inf;
        int t = inf;
        for (int i = 0; i < n; ++i) {
            s += nums[i];
            mi = min(mi, nums[i]);
            if (i + 1 < n) {
                t = min(t, nums[i] + nums[i + 1]);
            }
        }
        if (n % 2 == 1) {
            return s - mi;
        }
        return s - t;
    }
};