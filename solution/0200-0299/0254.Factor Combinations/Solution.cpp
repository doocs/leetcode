class Solution {
public:
    vector<vector<int>> getFactors(int n) {
        vector<int> t;
        vector<vector<int>> ans;
        function<void(int, int)> dfs = [&](int n, int i) {
            if (t.size()) {
                vector<int> cp = t;
                cp.emplace_back(n);
                ans.emplace_back(cp);
            }
            for (int j = i; j <= n / j; ++j) {
                if (n % j == 0) {
                    t.emplace_back(j);
                    dfs(n / j, j);
                    t.pop_back();
                }
            }
        };
        dfs(n, 2);
        return ans;
    }
};