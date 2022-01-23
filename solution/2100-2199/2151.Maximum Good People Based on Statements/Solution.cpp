class Solution {
public:
    int n;
    vector<vector<int>> statements;

    int maximumGood(vector<vector<int>>& statements) {
        n = statements.size();
        this->statements = statements;
        int ans = 0;
        for (int k = 0; k < (1 << n); ++k) ans = max(ans, check(k));
        return ans;    
    }

    int check(int k) {
        int cnt = 0;
        for (int i = 0; i < n; ++i)
        {
            if ((k >> i) & 1)
            {
                for (int j = 0; j < n; ++j)
                {
                    if (statements[i][j] < 2 && ((k >> j) & 1) != statements[i][j]) return 0;
                }
                ++cnt;
            }
        }
        return cnt;
    }
};