class Solution {
public:
    vector<string> beforeAndAfterPuzzles(vector<string>& phrases) {
        int n = phrases.size();
        pair<string, string> ps[n];
        for (int i = 0; i < n; ++i) {
            int j = phrases[i].find(' ');
            if (j == string::npos) {
                ps[i] = {phrases[i], phrases[i]};
            } else {
                int k = phrases[i].rfind(' ');
                ps[i] = {phrases[i].substr(0, j), phrases[i].substr(k + 1)};
            }
        }
        unordered_set<string> s;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i != j && ps[i].second == ps[j].first) {
                    s.insert(phrases[i] + phrases[j].substr(ps[i].second.size()));
                }
            }
        }
        vector<string> ans(s.begin(), s.end());
        sort(ans.begin(), ans.end());
        return ans;
    }
};