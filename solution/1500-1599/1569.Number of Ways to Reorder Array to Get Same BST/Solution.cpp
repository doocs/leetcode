class Solution {
public:
    int numOfWays(vector<int>& nums) {
        int n = nums.size();
        const int mod = 1e9 + 7;
        int c[n][n];
        memset(c, 0, sizeof(c));
        c[0][0] = 1;
        for (int i = 1; i < n; ++i) {
            c[i][0] = 1;
            for (int j = 1; j <= i; ++j) {
                c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % mod;
            }
        }
        function<int(vector<int>)> dfs = [&](vector<int> nums) -> int {
            if (nums.size() < 2) {
                return 1;
            }
            vector<int> left, right;
            for (int& x : nums) {
                if (x < nums[0]) {
                    left.push_back(x);
                } else if (x > nums[0]) {
                    right.push_back(x);
                }
            }
            int m = left.size(), n = right.size();
            int a = dfs(left), b = dfs(right);
            return c[m + n][m] * 1ll * a % mod * b % mod;
        };
        return (dfs(nums) - 1 + mod) % mod;
    }
};