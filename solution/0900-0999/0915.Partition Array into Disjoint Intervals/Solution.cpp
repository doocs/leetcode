class Solution {
public:
    int partitionDisjoint(vector<int>& nums) {
        int n = nums.size();
        vector<int> mi(n + 1, INT_MAX);
        for (int i = n - 1; ~i; --i) mi[i] = min(nums[i], mi[i + 1]);
        int mx = 0;
        for (int i = 1; i <= n; ++i) {
            int v = nums[i - 1];
            mx = max(mx, v);
            if (mx <= mi[i]) return i;
        }
        return 0;
    }
};