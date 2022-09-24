using pii = pair<int, int>;

class Solution {
public:
    vector<string> computeSimilarities(vector<vector<int>>& docs) {
        double eps = 1e-9;
        unordered_map<int, vector<int>> d;
        for (int i = 0; i < docs.size(); ++i) {
            for (int v : docs[i]) {
                d[v].push_back(i);
            }
        }
        map<pii, int> cnt;
        for (auto& [_, ids] : d) {
            int n = ids.size();
            for (int i = 0; i < n; ++i) {
                for (int j = i + 1; j < n; ++j) {
                    cnt[{ids[i], ids[j]}]++;
                }
            }
        }
        vector<string> ans;
        for (auto& [k, v] : cnt) {
            auto [i, j] = k;
            int tot = docs[i].size() + docs[j].size() - v;
            double x = (double) v / tot + eps;
            char t[20];
            sprintf(t, "%d,%d: %0.4lf", i, j, x);
            ans.push_back(t);
        }
        return ans;
    }
};