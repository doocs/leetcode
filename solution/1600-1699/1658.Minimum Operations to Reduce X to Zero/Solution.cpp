class Solution {
public:
    int minOperations(vector<int>& nums, int x) {
        x = accumulate(nums.begin(), nums.end(), 0) - x;
        unordered_map<int, int> vis{{0, -1}};
        int n = nums.size();
        int ans = 1 << 30;
        for (int i = 0, s = 0; i < n; ++i) {
            s += nums[i];
            if (!vis.count(s)) {
                vis[s] = i;
            }
            if (vis.count(s - x)) {
                int j = vis[s - x];
                ans = min(ans, n - (i - j));
            }
        }
        return ans == 1 << 30 ? -1 : ans;
    }
};