class Solution {
public:
    int subsetXORSum(vector<int>& nums) {
        int n = nums.size();
        int ans = 0;
        function<void(int, int)> dfs = [&](int i, int s) {
            if (i >= n) {
                ans += s;
                return;
            }
            dfs(i + 1, s);
            dfs(i + 1, s ^ nums[i]);
        };
        dfs(0, 0);
        return ans;
    }
};