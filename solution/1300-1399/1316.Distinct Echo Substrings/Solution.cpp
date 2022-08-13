typedef unsigned long long ull;

class Solution {
public:
    int distinctEchoSubstrings(string text) {
        int n = text.size();
        int base = 131;
        vector<ull> p(n + 10);
        vector<ull> h(n + 10);
        p[0] = 1;
        for (int i = 0; i < n; ++i) {
            int t = text[i] - 'a' + 1;
            p[i + 1] = p[i] * base;
            h[i + 1] = h[i] * base + t;
        }
        unordered_set<ull> vis;
        for (int i = 0; i < n - 1; ++i) {
            for (int j = i + 1; j < n; j += 2) {
                int k = (i + j) >> 1;
                ull a = get(i + 1, k + 1, p, h);
                ull b = get(k + 2, j + 1, p, h);
                if (a == b) vis.insert(a);
            }
        }
        return vis.size();
    }

    ull get(int l, int r, vector<ull>& p, vector<ull>& h) {
        return h[r] - h[l - 1] * p[r - l + 1];
    }
};