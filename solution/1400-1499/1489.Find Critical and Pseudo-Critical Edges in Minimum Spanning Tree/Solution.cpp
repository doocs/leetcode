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
        if (find(a) == find(b)) return false;
        p[find(a)] = find(b);
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
    vector<vector<int>> findCriticalAndPseudoCriticalEdges(int n, vector<vector<int>>& edges) {
        for (int i = 0; i < edges.size(); ++i) edges[i].push_back(i);
        sort(edges.begin(), edges.end(), [](auto& a, auto& b) { return a[2] < b[2]; });
        int v = 0;
        UnionFind uf(n);
        for (auto& e : edges) {
            int f = e[0], t = e[1], w = e[2];
            if (uf.unite(f, t)) v += w;
        }
        vector<vector<int>> ans(2);
        for (auto& e : edges) {
            int f = e[0], t = e[1], w = e[2], i = e[3];
            UnionFind ufa(n);
            int k = 0;
            for (auto& ne : edges) {
                int x = ne[0], y = ne[1], z = ne[2], j = ne[3];
                if (j != i && ufa.unite(x, y)) k += z;
            }
            if (ufa.n > 1 || (ufa.n == 1 && k > v)) {
                ans[0].push_back(i);
                continue;
            }

            UnionFind ufb(n);
            ufb.unite(f, t);
            k = w;
            for (auto& ne : edges) {
                int x = ne[0], y = ne[1], z = ne[2], j = ne[3];
                if (j != i && ufb.unite(x, y)) k += z;
            }
            if (k == v) ans[1].push_back(i);
        }
        return ans;
    }
};