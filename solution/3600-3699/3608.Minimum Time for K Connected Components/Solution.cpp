class UnionFind {
public:
    vector<int> p;
    vector<int> size;

    UnionFind(int n) {
        p.resize(n);
        size.resize(n, 1);
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
    }

    int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    bool unite(int a, int b) {
        int pa = find(a);
        int pb = find(b);
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
};

class Solution {
public:
    int minTime(int n, vector<vector<int>>& edges, int k) {
        sort(edges.begin(), edges.end(), [](const vector<int>& a, const vector<int>& b) {
            return a[2] < b[2];
        });

        UnionFind uf(n);
        int cnt = n;

        for (int i = edges.size() - 1; i >= 0; i--) {
            int u = edges[i][0];
            int v = edges[i][1];
            int t = edges[i][2];

            if (uf.unite(u, v)) {
                cnt--;
                if (cnt < k) {
                    return t;
                }
            }
        }
        return 0;
    }
};
