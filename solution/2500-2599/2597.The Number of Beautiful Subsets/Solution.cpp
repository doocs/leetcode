class Solution {
public:
    int beautifulSubsets(vector<int>& nums, int k) {
        int ans = -1;
        int cnt[1010]{};
        int n = nums.size();

        function<void(int)> dfs = [&](int i) {
            if (i >= n) {
                ++ans;
                return;
            }
            dfs(i + 1);
            bool ok1 = nums[i] + k >= 1010 || cnt[nums[i] + k] == 0;
            bool ok2 = nums[i] - k < 0 || cnt[nums[i] - k] == 0;
            if (ok1 && ok2) {
                ++cnt[nums[i]];
                dfs(i + 1);
                --cnt[nums[i]];
            }
        };
        dfs(0);
        return ans;
    }
};