class Solution {
public:
    int maximumGood(vector<vector<int>>& statements) {
        int ans = 0;
        for (int mask = 1; mask < 1 << statements.size(); ++mask) ans = max(ans, check(mask, statements));
        return ans;
    }

    int check(int mask, vector<vector<int>>& statements) {
        int cnt = 0;
        int n = statements.size();
        for (int i = 0; i < n; ++i) {
            if ((mask >> i) & 1) {
                for (int j = 0; j < n; ++j) {
                    int v = statements[i][j];
                    if (v < 2 && ((mask >> j) & 1) != v) return 0;
                }
                ++cnt;
            }
        }
        return cnt;
    }
};