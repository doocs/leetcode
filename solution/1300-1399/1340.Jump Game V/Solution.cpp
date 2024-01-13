class Solution {
public:
    int maxJumps(vector<int>& arr, int d) {
        int n = arr.size();
        int f[n];
        memset(f, 0, sizeof(f));
        function<int(int)> dfs = [&](int i) -> int {
            if (f[i]) {
                return f[i];
            }
            int ans = 1;
            for (int j = i - 1; j >= 0; --j) {
                if (i - j > d || arr[j] >= arr[i]) {
                    break;
                }
                ans = max(ans, 1 + dfs(j));
            }
            for (int j = i + 1; j < n; ++j) {
                if (j - i > d || arr[j] >= arr[i]) {
                    break;
                }
                ans = max(ans, 1 + dfs(j));
            }
            return f[i] = ans;
        };
        int ans = 1;
        for (int i = 0; i < n; ++i) {
            ans = max(ans, dfs(i));
        }
        return ans;
    }
};