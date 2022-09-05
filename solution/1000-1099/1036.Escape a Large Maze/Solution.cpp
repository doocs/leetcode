typedef unsigned long long ULL;

class Solution {
public:
    vector<vector<int>> dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    unordered_set<ULL> blocked;
    int N = 1e6;

    bool isEscapePossible(vector<vector<int>>& blocked, vector<int>& source, vector<int>& target) {
        this->blocked.clear();
        for (auto& b : blocked) this->blocked.insert((ULL) b[0] * N + b[1]);
        unordered_set<ULL> s1;
        unordered_set<ULL> s2;
        return dfs(source, target, s1) && dfs(target, source, s2);
    }

    bool dfs(vector<int>& source, vector<int>& target, unordered_set<ULL>& seen) {
        int sx = source[0], sy = source[1];
        int tx = target[0], ty = target[1];
        if (sx < 0 || sx >= N || sy < 0 || sy >= N || tx < 0 || tx >= N || ty < 0 || ty >= N || blocked.count((ULL) sx * N + sy) || seen.count((ULL) sx * N + sy)) return 0;
        seen.insert((ULL) sx * N + sy);
        if (seen.size() > 20000 || (sx == target[0] && sy == target[1])) return 1;
        for (auto& dir : dirs) {
            vector<int> next = {sx + dir[0], sy + dir[1]};
            if (dfs(next, target, seen)) return 1;
        }
        return 0;
    }
};