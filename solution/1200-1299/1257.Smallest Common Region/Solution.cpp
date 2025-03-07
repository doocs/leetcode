class Solution {
public:
    string findSmallestRegion(vector<vector<string>>& regions, string region1, string region2) {
        unordered_map<string, string> g;
        for (const auto& r : regions) {
            string x = r[0];
            for (size_t i = 1; i < r.size(); ++i) {
                g[r[i]] = x;
            }
        }
        unordered_set<string> s;
        for (string x = region1; !x.empty(); x = g[x]) {
            s.insert(x);
        }
        string x = region2;
        while (!g[x].empty() && s.find(x) == s.end()) {
            x = g[x];
        }
        return x;
    }
};
