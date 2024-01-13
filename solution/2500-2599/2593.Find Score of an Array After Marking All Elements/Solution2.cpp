class Solution {
public:
    long long findScore(vector<int>& nums) {
        int n = nums.size();
        vector<int> idx(n);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) {
            return nums[i] < nums[j] || (nums[i] == nums[j] && i < j);
        });
        long long ans = 0;
        vector<bool> vis(n + 2);
        for (int i : idx) {
            if (!vis[i + 1]) {
                ans += nums[i];
                vis[i] = vis[i + 2] = true;
            }
        }
        return ans;
    }
};