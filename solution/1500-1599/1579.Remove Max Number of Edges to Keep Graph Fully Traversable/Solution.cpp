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
        int pa = find(a - 1), pb = find(b - 1);
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
    int maxNumEdgesToRemove(int n, vector<vector<int>>& edges) {
        UnionFind ufa(n), ufb(n);
        int ans = 0;
        for (auto& e : edges) {
            if (e[0] == 3) {
                if (ufa.unite(e[1], e[2]))
                    ufb.unite(e[1], e[2]);
                else
                    ++ans;
            }
        }
        for (auto& e : edges)
            if ((e[0] == 1 && !ufa.unite(e[1], e[2])) || (e[0] == 2 && !ufb.unite(e[1], e[2])))
                ++ans;
        return ufa.n == 1 && ufb.n == 1 ? ans : -1;
    }
};