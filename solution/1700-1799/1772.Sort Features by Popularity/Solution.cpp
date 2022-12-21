class Solution {
public:
    vector<string> sortFeatures(vector<string>& features, vector<string>& responses) {
        unordered_map<string, int> cnt;
        for (auto& r : responses) {
            stringstream ss(r);
            string t;
            unordered_set<string> ws;
            while (ss >> t) {
                ws.insert(t);
            }
            for (auto& w : ws) {
                cnt[w]++;
            }
        }
        int n = features.size();
        vector<int> idx(n);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) -> bool {
            int d = cnt[features[i]] - cnt[features[j]];
            return d > 0 || (d == 0 && i < j);
        });
        vector<string> ans(n);
        for (int i = 0; i < n; ++i) {
            ans[i] = features[idx[i]];
        }
        return ans;
    }
};