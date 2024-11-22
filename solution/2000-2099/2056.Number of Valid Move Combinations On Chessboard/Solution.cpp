class Solution {
public:
    int countCombinations(vector<string>& pieces, vector<vector<int>>& positions) {
        int n = pieces.size();
        const int m = 9;
        int ans = 0;

        vector<vector<vector<int>>> dist(n, vector<vector<int>>(m, vector<int>(m, -1)));
        vector<vector<int>> end(n, vector<int>(3));

        const int rookDirs[4][2] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        const int bishopDirs[4][2] = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        const int queenDirs[8][2] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        auto resetDist = [&](int i) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < m; k++) {
                    dist[i][j][k] = -1;
                }
            }
        };

        auto checkStop = [&](int i, int x, int y, int t) -> bool {
            for (int j = 0; j < i; j++) {
                if (dist[j][x][y] >= t) {
                    return false;
                }
            }
            return true;
        };

        auto checkPass = [&](int i, int x, int y, int t) -> bool {
            for (int j = 0; j < i; j++) {
                if (dist[j][x][y] == t) {
                    return false;
                }
                if (end[j][0] == x && end[j][1] == y && end[j][2] <= t) {
                    return false;
                }
            }
            return true;
        };

        auto isValid = [&](int x, int y) -> bool {
            return x >= 1 && x < m && y >= 1 && y < m;
        };

        auto getDirs = [&](const string& piece) -> const int(*)[2] {
            char c = piece[0];
            if (c == 'r') {
                return rookDirs;
            }
            if (c == 'b') {
                return bishopDirs;
            }
            return queenDirs;
        };

        auto dfs = [&](auto&& dfs, int i) -> void {
            if (i >= n) {
                ans++;
                return;
            }

            int x = positions[i][0], y = positions[i][1];
            resetDist(i);
            dist[i][x][y] = 0;
            end[i] = {x, y, 0};

            if (checkStop(i, x, y, 0)) {
                dfs(dfs, i + 1);
            }

            const int(*dirs)[2] = getDirs(pieces[i]);
            int dirsSize = (pieces[i][0] == 'q') ? 8 : 4;

            for (int d = 0; d < dirsSize; d++) {
                resetDist(i);
                dist[i][x][y] = 0;
                int nx = x + dirs[d][0], ny = y + dirs[d][1], nt = 1;

                while (isValid(nx, ny) && checkPass(i, nx, ny, nt)) {
                    dist[i][nx][ny] = nt;
                    end[i] = {nx, ny, nt};
                    if (checkStop(i, nx, ny, nt)) {
                        dfs(dfs, i + 1);
                    }
                    nx += dirs[d][0];
                    ny += dirs[d][1];
                    nt++;
                }
            }
        };

        dfs(dfs, 0);
        return ans;
    }
};
