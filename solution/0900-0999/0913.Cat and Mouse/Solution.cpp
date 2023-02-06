const int HOLE = 0;
const int MOUSE_START = 1;
const int CAT_START = 2;
const int MOUSE_TURN = 0;
const int CAT_TURN = 1;
const int MOUSE_WIN = 1;
const int CAT_WIN = 2;
const int TIE = 0;

class Solution {
public:
    int catMouseGame(vector<vector<int>>& graph) {
        int n = graph.size();
        int res[n][n][2];
        int degree[n][n][2];
        memset(res, 0, sizeof res);
        memset(degree, 0, sizeof degree);
        for (int i = 0; i < n; ++i) {
            for (int j = 1; j < n; ++j) {
                degree[i][j][MOUSE_TURN] = graph[i].size();
                degree[i][j][CAT_TURN] = graph[j].size();
            }
            for (int j : graph[HOLE]) {
                --degree[i][j][CAT_TURN];
            }
        }
        auto getPrevStates = [&](int m, int c, int t) {
            int pt = t ^ 1;
            vector<tuple<int, int, int>> pre;
            if (pt == CAT_TURN) {
                for (int pc : graph[c]) {
                    if (pc != HOLE) {
                        pre.emplace_back(m, pc, pt);
                    }
                }
            } else {
                for (int pm : graph[m]) {
                    pre.emplace_back(pm, c, pt);
                }
            }
            return pre;
        };
        queue<tuple<int, int, int>> q;
        for (int j = 1; j < n; ++j) {
            res[0][j][MOUSE_TURN] = res[0][j][CAT_TURN] = MOUSE_WIN;
            q.emplace(0, j, MOUSE_TURN);
            q.emplace(0, j, CAT_TURN);
        }
        for (int i = 1; i < n; ++i) {
            res[i][i][MOUSE_TURN] = res[i][i][CAT_TURN] = CAT_WIN;
            q.emplace(i, i, MOUSE_TURN);
            q.emplace(i, i, CAT_TURN);
        }
        while (!q.empty()) {
            auto [m, c, t] = q.front();
            q.pop();
            int x = res[m][c][t];
            for (auto [pm, pc, pt] : getPrevStates(m, c, t)) {
                if (res[pm][pc][pt] == TIE) {
                    bool win = (x == MOUSE_WIN && pt == MOUSE_TURN) || (x == CAT_WIN && pt == CAT_TURN);
                    if (win) {
                        res[pm][pc][pt] = x;
                        q.emplace(pm, pc, pt);
                    } else {
                        if (--degree[pm][pc][pt] == 0) {
                            res[pm][pc][pt] = x;
                            q.emplace(pm, pc, pt);
                        }
                    }
                }
            }
        }
        return res[MOUSE_START][CAT_START][MOUSE_TURN];
    }
};