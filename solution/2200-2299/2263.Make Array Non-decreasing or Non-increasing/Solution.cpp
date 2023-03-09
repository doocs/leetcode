class Solution {
public:
    int convertArray(vector<int>& nums) {
        int a = solve(nums);
        reverse(nums.begin(), nums.end());
        int b = solve(nums);
        return min(a, b);
    }

    int solve(vector<int>& nums) {
        int n = nums.size();
        int f[n + 1][1001];
        memset(f, 0, sizeof(f));
        for (int i = 1; i <= n; ++i) {
            int mi = 1 << 30;
            for (int j = 0; j <= 1000; ++j) {
                mi = min(mi, f[i - 1][j]);
                f[i][j] = mi + abs(nums[i - 1] - j);
            }
        }
        return *min_element(f[n], f[n] + 1001);
    }
};