const int mx = 1e5 + 10;
bool prime[mx + 1];
int init = []() {
    for (int i = 2; i <= mx; ++i) prime[i] = true;
    for (int i = 2; i <= mx; ++i) {
        if (prime[i]) {
            for (int j = i + i; j <= mx; j += i) {
                prime[j] = false;
            }
        }
    }
    return 0;
}();

class UnionFind {
public:
    UnionFind(int n) {
        p = vector<int>(n);
        size = vector<int>(n, 1);
        iota(p.begin(), p.end(), 0);
    }

    bool unite(int a, int b) {
        int pa = find(a), pb = find(b);
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
        return true;
    }

    int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    int getSize(int x) {
        return size[find(x)];
    }

private:
    vector<int> p, size;
};

class Solution {
public:
    long long countPaths(int n, vector<vector<int>>& edges) {
        vector<int> g[n + 1];
        UnionFind uf(n + 1);
        for (auto& e : edges) {
            int u = e[0], v = e[1];
            g[u].push_back(v);
            g[v].push_back(u);
            if (!prime[u] && !prime[v]) {
                uf.unite(u, v);
            }
        }
        long long ans = 0;
        for (int i = 1; i <= n; ++i) {
            if (prime[i]) {
                long long t = 0;
                for (int j : g[i]) {
                    if (!prime[j]) {
                        long long cnt = uf.getSize(j);
                        ans += cnt;
                        ans += cnt * t;
                        t += cnt;
                    }
                }
            }
        }
        return ans;
    }
};