class Solution {
public:
    vector<int> p;

    bool validPath(int n, vector<vector<int>>& edges, int source, int destination) {
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        for (auto& e : edges) p[find(e[0])] = find(e[1]);
        return find(source) == find(destination);
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};