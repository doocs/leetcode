class Solution {
public:
    int maxSumDivThree(vector<int>& nums) {
        int n = nums.size();
        const int inf = 1 << 30;
        int f[n + 1][3];
        f[0][0] = 0;
        f[0][1] = f[0][2] = -inf;
        for (int i = 1; i <= n; ++i) {
            int x = nums[i - 1];
            for (int j = 0; j < 3; ++j) {
                f[i][j] = max(f[i - 1][j], f[i - 1][(j - x % 3 + 3) % 3] + x);
            }
        }
        return f[n][0];
    }
};