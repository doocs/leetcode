class Solution {
public:
    int maxOperations(vector<int>& nums) {
        int n = nums.size();
        int f[n][n];
        auto g = [&](int i, int j, int s) -> int {
            memset(f, -1, sizeof(f));
            function<int(int, int)> dfs = [&](int i, int j) -> int {
                if (j - i < 1) {
                    return 0;
                }
                if (f[i][j] != -1) {
                    return f[i][j];
                }
                int ans = 0;
                if (nums[i] + nums[i + 1] == s) {
                    ans = max(ans, 1 + dfs(i + 2, j));
                }
                if (nums[i] + nums[j] == s) {
                    ans = max(ans, 1 + dfs(i + 1, j - 1));
                }
                if (nums[j - 1] + nums[j] == s) {
                    ans = max(ans, 1 + dfs(i, j - 2));
                }
                return f[i][j] = ans;
            };
            return dfs(i, j);
        };
        int a = g(2, n - 1, nums[0] + nums[1]);
        int b = g(0, n - 3, nums[n - 2] + nums[n - 1]);
        int c = g(1, n - 2, nums[0] + nums[n - 1]);
        return 1 + max({a, b, c});
    }
};