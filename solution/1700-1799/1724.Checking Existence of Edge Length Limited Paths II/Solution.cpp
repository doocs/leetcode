class PersistentUnionFind {
private:
    vector<int> rank;
    vector<int> parent;
    vector<int> version;

public:
    PersistentUnionFind(int n)
        : rank(n, 0)
        , parent(n)
        , version(n, INT_MAX) {
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    int find(int x, int t) {
        if (parent[x] == x || version[x] >= t) {
            return x;
        }
        return find(parent[x], t);
    }

    bool unionSet(int a, int b, int t) {
        int pa = find(a, INT_MAX);
        int pb = find(b, INT_MAX);
        if (pa == pb) {
            return false;
        }
        if (rank[pa] > rank[pb]) {
            version[pb] = t;
            parent[pb] = pa;
        } else {
            version[pa] = t;
            parent[pa] = pb;
            if (rank[pa] == rank[pb]) {
                rank[pb]++;
            }
        }
        return true;
    }
};

class DistanceLimitedPathsExist {
private:
    PersistentUnionFind puf;

public:
    DistanceLimitedPathsExist(int n, vector<vector<int>>& edgeList)
        : puf(n) {
        sort(edgeList.begin(), edgeList.end(),
            [](const vector<int>& a, const vector<int>& b) {
                return a[2] < b[2];
            });

        for (const auto& edge : edgeList) {
            puf.unionSet(edge[0], edge[1], edge[2]);
        }
    }

    bool query(int p, int q, int limit) {
        return puf.find(p, limit) == puf.find(q, limit);
    }
};

/**
 * Your DistanceLimitedPathsExist object will be instantiated and called as such:
 * DistanceLimitedPathsExist* obj = new DistanceLimitedPathsExist(n, edgeList);
 * bool param_1 = obj->query(p,q,limit);
 */