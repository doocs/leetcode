class UnionFind {
public:
    vector<int> p;
    int n;

    UnionFind(int _n)
        : n(_n)
        , p(_n) {
        iota(p.begin(), p.end(), 0);
    }

    bool unite(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) return false;
        p[pa] = pb;
        --n;
        return true;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};

class Solution {
public:
    vector<int> findRedundantDirectedConnection(vector<vector<int>>& edges) {
        int n = edges.size();
        vector<int> p(n + 1);
        for (int i = 0; i <= n; ++i) p[i] = i;
        UnionFind uf(n + 1);
        int conflict = -1, cycle = -1;
        for (int i = 0; i < n; ++i) {
            int u = edges[i][0], v = edges[i][1];
            if (p[v] != v)
                conflict = i;
            else {
                p[v] = u;
                if (!uf.unite(u, v)) cycle = i;
            }
        }
        if (conflict == -1) return edges[cycle];
        int v = edges[conflict][1];
        if (cycle != -1) return {p[v], v};
        return edges[conflict];
    }
};