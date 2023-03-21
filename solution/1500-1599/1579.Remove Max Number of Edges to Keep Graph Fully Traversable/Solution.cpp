class UnionFind {
public:
    int cnt;

    UnionFind(int n) {
        p = vector<int>(n);
        size = vector<int>(n, 1);
        iota(p.begin(), p.end(), 0);
        cnt = n;
    }

    bool unite(int a, int b) {
        int pa = find(a - 1), pb = find(b - 1);
        if (pa == pb) {
            return false;
        }
        if (size[pa] > size[pb]) {
            p[pb] = pa;
            size[pa] += size[pb];
        } else {
            p[pa] = pb;
            size[pb] += size[pa];
        }
        --cnt;
        return true;
    }

    int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

private:
    vector<int> p, size;
};

class Solution {
public:
    int maxNumEdgesToRemove(int n, vector<vector<int>>& edges) {
        UnionFind ufa(n);
        UnionFind ufb(n);
        int ans = 0;
        for (auto& e : edges) {
            int t = e[0], u = e[1], v = e[2];
            if (t == 3) {
                if (ufa.unite(u, v)) {
                    ufb.unite(u, v);
                } else {
                    ++ans;
                }
            }
        }
        for (auto& e : edges) {
            int t = e[0], u = e[1], v = e[2];
            ans += t == 1 && !ufa.unite(u, v);
            ans += t == 2 && !ufb.unite(u, v);
        }
        return ufa.cnt == 1 && ufb.cnt == 1 ? ans : -1;
    }
};