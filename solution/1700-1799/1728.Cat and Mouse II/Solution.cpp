class Solution {
private:
    const int dirs[5] = {-1, 0, 1, 0, -1};

    int calc(vector<vector<int>>& gMouse, vector<vector<int>>& gCat, int mouseStart, int catStart, int hole) {
        int n = gMouse.size();
        vector<vector<vector<int>>> degree(n, vector<vector<int>>(n, vector<int>(2)));
        vector<vector<vector<int>>> ans(n, vector<vector<int>>(n, vector<int>(2)));
        queue<tuple<int, int, int>> q;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                degree[i][j][0] = gMouse[i].size();
                degree[i][j][1] = gCat[j].size();
            }
        }

        for (int i = 0; i < n; i++) {
            ans[hole][i][1] = 1;
            ans[i][hole][0] = 2;
            ans[i][i][1] = 2;
            ans[i][i][0] = 2;
            q.push(make_tuple(hole, i, 1));
            q.push(make_tuple(i, hole, 0));
            q.push(make_tuple(i, i, 0));
            q.push(make_tuple(i, i, 1));
        }

        while (!q.empty()) {
            auto state = q.front();
            q.pop();
            int m = get<0>(state), c = get<1>(state), t = get<2>(state);
            int result = ans[m][c][t];
            for (auto& prevState : getPrevStates(gMouse, gCat, state, ans)) {
                int pm = get<0>(prevState), pc = get<1>(prevState), pt = get<2>(prevState);
                if (pt == result - 1) {
                    ans[pm][pc][pt] = result;
                    q.push(prevState);
                } else {
                    degree[pm][pc][pt]--;
                    if (degree[pm][pc][pt] == 0) {
                        ans[pm][pc][pt] = result;
                        q.push(prevState);
                    }
                }
            }
        }

        return ans[mouseStart][catStart][0];
    }

    vector<tuple<int, int, int>> getPrevStates(vector<vector<int>>& gMouse, vector<vector<int>>& gCat, tuple<int, int, int>& state, vector<vector<vector<int>>>& ans) {
        int m = get<0>(state), c = get<1>(state), t = get<2>(state);
        int pt = t ^ 1;
        vector<tuple<int, int, int>> pre;
        if (pt == 1) {
            for (int pc : gCat[c]) {
                if (ans[m][pc][1] == 0) {
                    pre.push_back(make_tuple(m, pc, pt));
                }
            }
        } else {
            for (int pm : gMouse[m]) {
                if (ans[pm][c][0] == 0) {
                    pre.push_back(make_tuple(pm, c, 0));
                }
            }
        }
        return pre;
    }

public:
    bool canMouseWin(vector<string>& grid, int catJump, int mouseJump) {
        int m = grid.size();
        int n = grid[0].length();
        int catStart = 0, mouseStart = 0, food = 0;
        vector<vector<int>> gMouse(m * n);
        vector<vector<int>> gCat(m * n);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i][j];
                if (c == '#') {
                    continue;
                }
                int v = i * n + j;
                if (c == 'C') {
                    catStart = v;
                } else if (c == 'M') {
                    mouseStart = v;
                } else if (c == 'F') {
                    food = v;
                }

                for (int d = 0; d < 4; ++d) {
                    for (int k = 0; k <= mouseJump; k++) {
                        int x = i + k * dirs[d];
                        int y = j + k * dirs[d + 1];
                        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == '#') {
                            break;
                        }
                        gMouse[v].push_back(x * n + y);
                    }
                    for (int k = 0; k <= catJump; k++) {
                        int x = i + k * dirs[d];
                        int y = j + k * dirs[d + 1];
                        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == '#') {
                            break;
                        }
                        gCat[v].push_back(x * n + y);
                    }
                }
            }
        }

        return calc(gMouse, gCat, mouseStart, catStart, food) == 1;
    }
};
