class UnionFind {
public:
    unordered_map<long long, long long> p;
    unordered_map<long long, int> sz;

    long long find(long long x) {
        if (!p.count(x)) {
            p[x] = x;
            sz[x] = 1;
        }
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    bool unite(long long a, long long b) {
        long long pa = find(a), pb = find(b);
        if (pa == pb) return false;

        if (sz[pa] > sz[pb]) {
            p[pb] = pa;
            sz[pa] += sz[pb];
        } else {
            p[pa] = pb;
            sz[pb] += sz[pa];
        }
        return true;
    }
};

class Solution {
public:
    int maxActivated(vector<vector<int>>& points) {
        UnionFind uf;
        long long m = (long long) 3e9;

        for (auto& p : points) {
            uf.unite(p[0], p[1] + m);
        }

        unordered_map<long long, int> cnt;
        for (auto& p : points) {
            long long root = uf.find(p[0]);
            cnt[root]++;
        }

        int mx1 = 0, mx2 = 0;
        for (auto& [_, x] : cnt) {
            if (mx1 < x) {
                mx2 = mx1;
                mx1 = x;
            } else if (mx2 < x) {
                mx2 = x;
            }
        }

        return mx1 + mx2 + 1;
    }
};
