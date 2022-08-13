class Solution {
public:
    vector<int> p;
    int size;

    int regionsBySlashes(vector<string>& grid) {
        int n = grid.size();
        size = n * n * 4;
        p.resize(size);
        for (int i = 0; i < size; ++i) p[i] = i;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int k = i * n + j;
                if (i < n - 1) merge(4 * k + 2, (k + n) * 4);
                if (j < n - 1) merge(4 * k + 1, (k + 1) * 4 + 3);
                char v = grid[i][j];
                if (v == '/') {
                    merge(4 * k, 4 * k + 3);
                    merge(4 * k + 1, 4 * k + 2);
                } else if (v == '\\') {
                    merge(4 * k, 4 * k + 1);
                    merge(4 * k + 2, 4 * k + 3);
                } else {
                    merge(4 * k, 4 * k + 1);
                    merge(4 * k + 1, 4 * k + 2);
                    merge(4 * k + 2, 4 * k + 3);
                }
            }
        }
        return size;
    }

    void merge(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) return;
        p[pa] = pb;
        --size;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};