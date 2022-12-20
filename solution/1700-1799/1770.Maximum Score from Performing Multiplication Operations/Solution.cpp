class Solution {
public:
    int maximumScore(vector<int>& nums, vector<int>& multipliers) {
        int n = nums.size(), m = multipliers.size();
        int f[m][m];
        memset(f, 0x3f, sizeof f);
        function<int(int, int)> dfs = [&](int i, int j) -> int {
            if (i >= m || j >= m || (i + j) >= m) return 0;
            if (f[i][j] != 0x3f3f3f3f) return f[i][j];
            int k = i + j;
            int a = dfs(i + 1, j) + nums[i] * multipliers[k];
            int b = dfs(i, j + 1) + nums[n - j - 1] * multipliers[k];
            return f[i][j] = max(a, b);
        };
        return dfs(0, 0);
    }
};