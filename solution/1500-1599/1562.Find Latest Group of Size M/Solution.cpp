class Solution {
public:
    vector<int> p;
    vector<int> size;

    int findLatestStep(vector<int>& arr, int m) {
        int n = arr.size();
        if (m == n) return n;
        p.resize(n);
        size.assign(n, 1);
        for (int i = 0; i < n; ++i) p[i] = i;
        int ans = -1;
        vector<int> vis(n);
        for (int i = 0; i < n; ++i) {
            int v = arr[i] - 1;
            if (v && vis[v - 1]) {
                if (size[find(v - 1)] == m) ans = i;
                unite(v, v - 1);
            }
            if (v < n - 1 && vis[v + 1]) {
                if (size[find(v + 1)] == m) ans = i;
                unite(v, v + 1);
            }
            vis[v] = true;
        }
        return ans;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    void unite(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) return;
        p[pa] = pb;
        size[pb] += size[pa];
    }
};