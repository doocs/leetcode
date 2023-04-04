class Solution {
public:
    vector<int> gridIllumination(int n, vector<vector<int>>& lamps, vector<vector<int>>& queries) {
        auto f = [&](int i, int j) -> long long {
            return (long long) i * n + j;
        };
        unordered_set<long long> s;
        unordered_map<int, int> row, col, diag1, diag2;
        for (auto& lamp : lamps) {
            int i = lamp[0], j = lamp[1];
            if (!s.count(f(i, j))) {
                s.insert(f(i, j));
                row[i]++;
                col[j]++;
                diag1[i - j]++;
                diag2[i + j]++;
            }
        }
        int m = queries.size();
        vector<int> ans(m);
        for (int k = 0; k < m; ++k) {
            int i = queries[k][0], j = queries[k][1];
            if (row[i] > 0 || col[j] > 0 || diag1[i - j] > 0 || diag2[i + j] > 0) {
                ans[k] = 1;
            }
            for (int x = i - 1; x <= i + 1; ++x) {
                for (int y = j - 1; y <= j + 1; ++y) {
                    if (x < 0 || x >= n || y < 0 || y >= n || !s.count(f(x, y))) {
                        continue;
                    }
                    s.erase(f(x, y));
                    row[x]--;
                    col[y]--;
                    diag1[x - y]--;
                    diag2[x + y]--;
                }
            }
        }
        return ans;
    }
};