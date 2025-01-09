typedef struct DSU {
    unordered_map<int, int> par, rank;
    DSU(int n) {
        for (int i = 0; i < n; ++i) {
            par[i] = i;
            rank[i] = 0;
        }
    }

    void makeSet(int v) {
        par[v] = v;
        rank[v] = 1;
    }

    int find(int x) {
        if (par[x] == x) {
            return x;
        }
        return par[x] = find(par[x]);
    }

    void unionSet(int u, int v) {
        u = find(u);
        v = find(v);
        if (u != v) {
            if (rank[u] < rank[v]) swap(u, v);
            par[v] = u;
            if (rank[u] == rank[v]) rank[u]++;
        }
    }
} DSU;

class Solution {
public:
    int countComponents(vector<int>& nums, int threshold) {
        DSU dsu(threshold);
        for (auto& num : nums) {
            for (int j = num; j <= threshold; j += num) {
                dsu.unionSet(num, j);
            }
        }
        unordered_set<int> par;
        for (auto& num : nums) {
            if (num > threshold) {
                par.insert(num);
            } else {
                par.insert(dsu.find(num));
            }
        }
        return par.size();
    }
};
