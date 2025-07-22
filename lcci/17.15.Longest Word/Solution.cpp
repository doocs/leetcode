class Solution {
public:
    string longestWord(vector<string>& words) {
        unordered_set<string> s(words.begin(), words.end());
        ranges::sort(words, [&](const string& a, const string& b) {
            return a.size() > b.size() || (a.size() == b.size() && a < b);
        });
        auto dfs = [&](this auto&& dfs, string w) -> bool {
            if (w.empty()) {
                return true;
            }
            for (int k = 1; k <= w.size(); ++k) {
                if (s.contains(w.substr(0, k)) && dfs(w.substr(k))) {
                    return true;
                }
            }
            return false;
        };
        for (const string& w : words) {
            s.erase(w);
            if (dfs(w)) {
                return w;
            }
        }
        return "";
    }
};
