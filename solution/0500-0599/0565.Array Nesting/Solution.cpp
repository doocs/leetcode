class Solution {
public:
    int arrayNesting(vector<int>& nums) {
        int n = nums.size();
        vector<bool> vis(n);
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (vis[i]) continue;
            int cur = nums[i], m = 1;
            vis[cur] = true;
            while (nums[cur] != nums[i]) {
                cur = nums[cur];
                ++m;
                vis[cur] = true;
            }
            res = max(res, m);
        }
        return res;
    }
};