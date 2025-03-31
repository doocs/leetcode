class Solution {
public:
    long long mostPoints(vector<vector<int>>& questions) {
        int n = questions.size();
        long long f[n];
        memset(f, 0, sizeof(f));
        auto dfs = [&](this auto&& dfs, int i) -> long long {
            if (i >= n) {
                return 0;
            }
            if (f[i]) {
                return f[i];
            }
            int p = questions[i][0], b = questions[i][1];
            return f[i] = max(p + dfs(i + b + 1), dfs(i + 1));
        };
        return dfs(0);
    }
};