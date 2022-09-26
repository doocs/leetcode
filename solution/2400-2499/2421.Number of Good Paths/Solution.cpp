class Solution {
public:
    int numberOfGoodPaths(vector<int>& vals, vector<vector<int>>& edges) {
        int n = vals.size();
        vector<int> p(n);
        iota(p.begin(), p.end(), 0);
        function<int(int)> find;
        find = [&](int x) {
            if (p[x] != x) {
                p[x] = find(p[x]);
            }
            return p[x];
        };
        vector<vector<int>> g(n);
        for (auto& e : edges) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            g[b].push_back(a);
        }
        unordered_map<int, unordered_map<int, int>> size;
        vector<pair<int, int>> arr(n);
        for (int i = 0; i < n; ++i) {
            arr[i] = {vals[i], i};
            size[i][vals[i]] = 1;
        }
        sort(arr.begin(), arr.end());
        int ans = n;
        for (auto [v, a] : arr) {
            for (int b : g[a]) {
                if (vals[b] > v) {
                    continue;
                }
                int pa = find(a), pb = find(b);
                if (pa != pb) {
                    ans += size[pa][v] * size[pb][v];
                    p[pa] = pb;
                    size[pb][v] += size[pa][v];
                }
            }
        }
        return ans;
    }
};