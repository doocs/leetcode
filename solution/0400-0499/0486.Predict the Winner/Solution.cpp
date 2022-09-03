class Solution {
public:
    vector<vector<int>> f;
    vector<int> s;

    bool PredictTheWinner(vector<int>& nums) {
        int n = nums.size();
        s.resize(n + 1);
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        f.assign(n + 1, vector<int>(n + 1, -1));
        return dfs(0, n - 1) * 2 >= s[n];
    }

    int dfs(int i, int j) {
        if (i > j) return 0;
        if (f[i][j] != -1) return f[i][j];
        int a = min(dfs(i + 1, j), dfs(i, j - 1));
        int res = s[j + 1] - s[i] - a;
        f[i][j] = res;
        return res;
    }
};