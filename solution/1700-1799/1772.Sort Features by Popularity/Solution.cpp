class Solution {
public:
    vector<string> sortFeatures(vector<string>& features, vector<string>& responses) {
        unordered_map<string, int> cnt;
        for (auto& s : responses) {
            istringstream iss(s);
            string w;
            unordered_set<string> st;
            while (iss >> w) {
                st.insert(w);
            }
            for (auto& w : st) {
                ++cnt[w];
            }
        }
        int n = features.size();
        vector<int> idx(n);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) {
            int x = cnt[features[i]], y = cnt[features[j]];
            return x == y ? i < j : x > y;
        });
        vector<string> ans(n);
        for (int i = 0; i < n; ++i) {
            ans[i] = features[idx[i]];
        }
        return ans;
    }
};