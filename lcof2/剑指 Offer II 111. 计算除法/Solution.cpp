class Solution {
public:
    vector<int> p;
    vector<double> w;

    vector<double> calcEquation(vector<vector<string>>& equations, vector<double>& values, vector<vector<string>>& queries) {
        int n = equations.size();
        for (int i = 0; i < (n << 1); ++i) {
            p.push_back(i);
            w.push_back(1.0);
        }
        unordered_map<string, int> mp;
        int idx = 0;
        for (int i = 0; i < n; ++i) {
            auto e = equations[i];
            string a = e[0], b = e[1];
            if (mp.find(a) == mp.end()) mp[a] = idx++;
            if (mp.find(b) == mp.end()) mp[b] = idx++;
            int pa = find(mp[a]), pb = find(mp[b]);
            if (pa == pb) continue;
            p[pa] = pb;
            w[pa] = w[mp[b]] * values[i] / w[mp[a]];
        }
        int m = queries.size();
        vector<double> res;
        for (int i = 0; i < m; ++i) {
            string c = queries[i][0], d = queries[i][1];
            if (mp.find(c) == mp.end() || mp.find(d) == mp.end())
                res.push_back(-1.0);
            else {
                int pa = find(mp[c]), pb = find(mp[d]);
                res.push_back(pa == pb ? w[mp[c]] / w[mp[d]] : -1.0);
            }
        }
        return res;
    }

    int find(int x) {
        if (p[x] != x) {
            int origin = p[x];
            p[x] = find(p[x]);
            w[x] *= w[origin];
        }
        return p[x];
    }
};