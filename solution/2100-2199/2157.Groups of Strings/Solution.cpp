class Solution {
public:
    int mx, n;

    vector<int> groupStrings(vector<string>& words) {
        unordered_map<int, int> p;
        unordered_map<int, int> size;
        mx = 0;
        n = words.size();
        for (auto& word : words) {
            int x = 0;
            for (auto& c : word) x |= 1 << (c - 'a');
            p[x] = x;
            ++size[x];
            mx = max(mx, size[x]);
            if (size[x] > 1) --n;
        }
        for (auto& [x, _] : p) {
            for (int i = 0; i < 26; ++i) {
                unite(x, x ^ (1 << i), p, size);
                if ((x >> i) & 1) {
                    for (int j = 0; j < 26; ++j) {
                        if (((x >> j) & 1) == 0) unite(x, x ^ (1 << i) | (1 << j), p, size);
                    }
                }
            }
        }
        return {n, mx};
    }

    int find(int x, unordered_map<int, int>& p) {
        if (p[x] != x) p[x] = find(p[x], p);
        return p[x];
    }

    void unite(int a, int b, unordered_map<int, int>& p, unordered_map<int, int>& size) {
        if (!p.count(b)) return;
        int pa = find(a, p), pb = find(b, p);
        if (pa == pb) return;
        p[pa] = pb;
        size[pb] += size[pa];
        mx = max(mx, size[pb]);
        --n;
    }
};