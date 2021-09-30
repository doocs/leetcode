class Solution {
public:
    vector<int> p;

    bool possibleBipartition(int n, vector<vector<int>>& dislikes) {
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        unordered_map<int, vector<int>> mp;
        for (auto e : dislikes)
        {
            mp[e[0] - 1].push_back(e[1] - 1);
            mp[e[1] - 1].push_back(e[0] - 1);
        }
        for (int i = 0; i < n; ++i)
        {
            auto dis = mp[i];
            for (int j : dis)
            {
                if (find(i) == find(j)) return false;
                p[find(j)] = find(dis[0]);
            }
        }
        return true;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};