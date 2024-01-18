class Solution {
public:
    int mx;
    int ans;

    int countMaxOrSubsets(vector<int>& nums) {
        dfs(0, 0, nums);
        return ans;
    }

    void dfs(int u, int t, vector<int>& nums) {
        if (u == nums.size()) {
            if (t > mx) {
                mx = t;
                ans = 1;
            } else if (t == mx)
                ++ans;
            return;
        }
        dfs(u + 1, t, nums);
        dfs(u + 1, t | nums[u], nums);
    }
};