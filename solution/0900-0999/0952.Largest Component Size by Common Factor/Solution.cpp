class UnionFind {
public:
    vector<int> p;
    int n;

    UnionFind(int _n)
        : n(_n)
        , p(_n) {
        iota(p.begin(), p.end(), 0);
    }

    void unite(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa != pb) p[pa] = pb;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};

class Solution {
public:
    int largestComponentSize(vector<int>& nums) {
        int m = *max_element(nums.begin(), nums.end());
        UnionFind* uf = new UnionFind(m + 1);
        for (int v : nums) {
            int i = 2;
            while (i <= v / i) {
                if (v % i == 0) {
                    uf->unite(v, i);
                    uf->unite(v, v / i);
                }
                ++i;
            }
        }
        vector<int> cnt(m + 1);
        int ans = 0;
        for (int v : nums) {
            int t = uf->find(v);
            ++cnt[t];
            ans = max(ans, cnt[t]);
        }
        return ans;
    }
};