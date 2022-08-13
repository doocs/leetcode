class Solution {
public:
    int mx;
    int ans;
    vector<int> nums;

    int countMaxOrSubsets(vector<int>& nums) {
        this->nums = nums;
        mx = 0;
        ans = 0;
        for (int x : nums) mx |= x;
        dfs(0, 0);
        return ans;
    }

    void dfs(int i, int t) {
        if (i == nums.size()) {
            if (t == mx) ++ans;
            return;
        }
        dfs(i + 1, t);
        dfs(i + 1, t | nums[i]);
    }
};