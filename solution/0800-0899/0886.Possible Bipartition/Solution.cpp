class Solution {
public:
    vector<int> p;

    bool possibleBipartition(int n, vector<vector<int>>& dislikes) {
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        unordered_map<int, vector<int>> dis;
        for (auto& d : dislikes) {
            int a = d[0] - 1, b = d[1] - 1;
            dis[a].push_back(b);
            dis[b].push_back(a);
        }
        for (int i = 0; i < n; ++i) {
            for (int j : dis[i]) {
                if (find(i) == find(j)) return false;
                p[find(j)] = find(dis[i][0]);
            }
        }
        return true;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};