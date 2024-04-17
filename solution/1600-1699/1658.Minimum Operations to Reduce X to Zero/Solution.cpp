class Solution {
public:
    int minOperations(vector<int>& nums, int x) {
        int s = accumulate(nums.begin(), nums.end(), 0) - x;
        unordered_map<int, int> vis = {{0, -1}};
        int mx = -1, t = 0;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            t += nums[i];
            if (!vis.contains(t)) {
                vis[t] = i;
            }
            if (vis.contains(t - s)) {
                mx = max(mx, i - vis[t - s]);
            }
        }
        return mx == -1 ? -1 : n - mx;
    }
};