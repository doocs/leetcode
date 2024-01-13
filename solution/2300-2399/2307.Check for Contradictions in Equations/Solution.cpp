class Solution {
public:
    bool checkContradictions(vector<vector<string>>& equations, vector<double>& values) {
        unordered_map<string, int> d;
        int n = 0;
        for (auto& e : equations) {
            for (auto& s : e) {
                if (!d.count(s)) {
                    d[s] = n++;
                }
            }
        }
        vector<int> p(n);
        iota(p.begin(), p.end(), 0);
        vector<double> w(n, 1.0);
        function<int(int)> find = [&](int x) -> int {
            if (p[x] != x) {
                int root = find(p[x]);
                w[x] *= w[p[x]];
                p[x] = root;
            }
            return p[x];
        };
        for (int i = 0; i < equations.size(); ++i) {
            int a = d[equations[i][0]], b = d[equations[i][1]];
            double v = values[i];
            int pa = find(a), pb = find(b);
            if (pa != pb) {
                p[pb] = pa;
                w[pb] = v * w[a] / w[b];
            } else if (fabs(v * w[a] - w[b]) >= 1e-5) {
                return true;
            }
        }
        return false;
    }
};