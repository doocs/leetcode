class Solution {
public:
    bool canPartitionGrid(vector<vector<int>>& grid) {
        return check(grid) || check(rotate(grid));
    }

private:
    bool check(const vector<vector<int>>& g) {
        int m = g.size(), n = g[0].size();
        long long s1 = 0, s2 = 0;

        unordered_map<long long, int> cnt1, cnt2;

        for (auto& row : g) {
            for (int x : row) {
                s2 += x;
                cnt2[x]++;
            }
        }

        for (int i = 0; i < m - 1; i++) {
            for (int x : g[i]) {
                s1 += x;
                s2 -= x;
                cnt1[x]++;
                cnt2[x]--;
            }

            if (s1 == s2) return true;

            if (s1 < s2) {
                long long diff = s2 - s1;
                if (cnt2[diff] > 0) {
                    if (
                        (m - i - 1 > 1 && n > 1) || (i == m - 2 && (g[i + 1][0] == diff || g[i + 1][n - 1] == diff)) || (n == 1 && (g[i + 1][0] == diff || g[m - 1][0] == diff))) return true;
                }
            } else {
                long long diff = s1 - s2;
                if (cnt1[diff] > 0) {
                    if (
                        (i + 1 > 1 && n > 1) || (i == 0 && (g[0][0] == diff || g[0][n - 1] == diff)) || (n == 1 && (g[0][0] == diff || g[i][0] == diff))) return true;
                }
            }
        }

        return false;
    }

    vector<vector<int>> rotate(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> t(n, vector<int>(m));
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                t[j][i] = grid[i][j];
            }
        }
        return t;
    }
};
