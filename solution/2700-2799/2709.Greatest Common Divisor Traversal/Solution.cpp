int MX = 100010;
vector<int> P[100010];

int init = []() {
    for (int x = 1; x < MX; ++x) {
        int v = x;
        int i = 2;
        while (i <= v / i) {
            if (v % i == 0) {
                P[x].push_back(i);
                while (v % i == 0) {
                    v /= i;
                }
            }
            ++i;
        }
        if (v > 1) {
            P[x].push_back(v);
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

private:
    vector<int> p, size;
};

class Solution {
public:
    bool canTraverseAllPairs(vector<int>& nums) {
        int m = *max_element(nums.begin(), nums.end());
        int n = nums.size();
        UnionFind uf(m + n + 1);
        for (int i = 0; i < n; ++i) {
            for (int j : P[nums[i]]) {
                uf.unite(i, j + n);
            }
        }
        unordered_set<int> s;
        for (int i = 0; i < n; ++i) {
            s.insert(uf.find(i));
        }
        return s.size() == 1;
    }
};