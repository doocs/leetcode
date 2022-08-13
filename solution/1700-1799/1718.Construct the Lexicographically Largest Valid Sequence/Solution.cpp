class Solution {
public:
    int n;
    vector<int> cnt, path;

    vector<int> constructDistancedSequence(int _n) {
        n = _n;
        cnt.resize(n * 2, 2);
        path.resize(n * 2);
        cnt[1] = 1;
        dfs(1);
        vector<int> ans;
        for (int i = 1; i < n * 2; ++i) ans.push_back(path[i]);
        return ans;
    }

    bool dfs(int u) {
        if (u == n * 2) return 1;
        if (path[u]) return dfs(u + 1);
        for (int i = n; i > 1; --i) {
            if (cnt[i] && u + i < n * 2 && !path[u + i]) {
                path[u] = path[u + i] = i;
                cnt[i] = 0;
                if (dfs(u + 1)) return 1;
                cnt[i] = 2;
                path[u] = path[u + i] = 0;
            }
        }
        if (cnt[1]) {
            path[u] = 1;
            cnt[1] = 0;
            if (dfs(u + 1)) return 1;
            cnt[1] = 1;
            path[u] = 0;
        }
        return 0;
    }
};