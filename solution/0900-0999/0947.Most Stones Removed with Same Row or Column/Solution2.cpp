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

private:
    vector<int> p, size;
};

class Solution {
public:
    int removeStones(vector<vector<int>>& stones) {
        int m = 10001;
        UnionFind uf(m << 1);
        for (auto& st : stones) {
            uf.unite(st[0], st[1] + m);
        }
        unordered_set<int> s;
        for (auto& st : stones) {
            s.insert(uf.find(st[0]));
        }
        return stones.size() - s.size();
    }
};