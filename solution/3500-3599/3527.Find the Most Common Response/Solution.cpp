class Solution {
public:
    string findCommonResponse(vector<vector<string>>& responses) {
        unordered_map<string, int> cnt;
        for (const auto& ws : responses) {
            unordered_set<string> s;
            for (const auto& w : ws) {
                if (s.insert(w).second) {
                    ++cnt[w];
                }
            }
        }
        string ans = responses[0][0];
        for (const auto& e : cnt) {
            const string& w = e.first;
            int v = e.second;
            if (cnt[ans] < v || (cnt[ans] == v && w < ans)) {
                ans = w;
            }
        }
        return ans;
    }
};