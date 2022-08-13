class Solution {
public:
    int minimumDifference(vector<int>& nums) {
        int n = nums.size() >> 1;
        vector<vector<int>> f(n + 1), g(n + 1);
        for (int i = 0; i < (1 << n); ++i) {
            int s = 0, cnt = 0;
            int s1 = 0, cnt1 = 0;
            for (int j = 0; j < n; ++j) {
                if (i & (1 << j)) {
                    s += nums[j];
                    ++cnt;
                    s1 += nums[n + j];
                    ++cnt1;
                } else {
                    s -= nums[j];
                    s1 -= nums[n + j];
                }
            }
            f[cnt].push_back(s);
            g[cnt1].push_back(s1);
        }
        for (int i = 0; i <= n; ++i) {
            sort(f[i].begin(), f[i].end());
            sort(g[i].begin(), g[i].end());
        }
        int ans = INT_MAX;
        for (int i = 0; i <= n; ++i) {
            for (int a : f[i]) {
                int left = 0, right = g[n - i].size() - 1;
                int b = -a;
                while (left < right) {
                    int mid = (left + right) >> 1;
                    if (g[n - i][mid] >= b)
                        right = mid;
                    else
                        left = mid + 1;
                }
                ans = min(ans, abs(a + g[n - i][left]));
                if (left > 0) ans = min(ans, abs(a + g[n - i][left - 1]));
            }
        }
        return ans;
    }
};