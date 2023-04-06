class Solution {
public:
    vector<vector<int>> ballGame(int num, vector<string>& plate) {
        int m = plate.size(), n = plate[0].size();
        vector<vector<int>> ans;
        int dirs[5] = {0, 1, 0, -1, 0};
        auto check = [&](int i, int j, int d) -> bool {
            int k = num;
            while (plate[i][j] != 'O') {
                if (k == 0) {
                    return false;
                }
                if (plate[i][j] == 'W') {
                    d = (d + 3) % 4;
                } else if (plate[i][j] == 'E') {
                    d = (d + 1) % 4;
                }
                i += dirs[d];
                j += dirs[d + 1];
                if (i < 0 || i >= m || j < 0 || j >= n) {
                    return false;
                }
                --k;
            }
            return true;
        };
        for (int i = 1; i < m - 1; ++i) {
            if (plate[i][0] == '.' && check(i, 0, 0)) {
                ans.push_back({i, 0});
            }
            if (plate[i][n - 1] == '.' && check(i, n - 1, 2)) {
                ans.push_back({i, n - 1});
            }
        }
        for (int j = 1; j < n - 1; ++j) {
            if (plate[0][j] == '.' && check(0, j, 1)) {
                ans.push_back({0, j});
            }
            if (plate[m - 1][j] == '.' && check(m - 1, j, 3)) {
                ans.push_back({m - 1, j});
            }
        }
        return ans;
    }
};