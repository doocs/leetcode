class Solution {
public:
    vector<int> p;

    int regionsBySlashes(vector<string>& grid) {
        int n = grid.size();
        for (int i = 0; i < n * n * 4; ++i) p.push_back(i);
        for (int i = 0; i < n; ++i) {
            string row = grid[i];
            for (int j = 0; j < n; ++j) {
                int idx = i * n + j;
                if (i < n - 1) p[find(idx * 4 + 2)] = find((idx + n) * 4);
                if (j < n - 1) p[find(idx * 4 + 1)] = find((idx + 1) * 4 + 3);
                if (row[j] == '/')
                {
                    p[find(idx * 4)] = find(idx * 4 + 3);
                    p[find(idx * 4 + 1)] = find(idx * 4 + 2);
                }
                else if (row[j] == '\\')
                {
                    p[find(idx * 4)] = find(idx * 4 + 1);
                    p[find(idx * 4 + 2)] = find(idx * 4 + 3);
                }
                else
                {
                    p[find(idx * 4)] = find(idx * 4 + 1);
                    p[find(idx * 4 + 1)] = find(idx * 4 + 2);
                    p[find(idx * 4 + 2)] = find(idx * 4 + 3);
                }
            }
        }
        unordered_set<int> s;
        for (int i = 0; i < p.size(); ++i)
            s.insert(find(i));
        return s.size();
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};