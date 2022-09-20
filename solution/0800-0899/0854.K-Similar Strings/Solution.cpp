class Solution {
public:
    int kSimilarity(string s1, string s2) {
        queue<string> q{{s1}};
        unordered_set<string> vis{{s1}};
        int ans = 0;
        while (1) {
            for (int i = q.size(); i; --i) {
                auto s = q.front();
                q.pop();
                if (s == s2) {
                    return ans;
                }
                for (auto& nxt : next(s, s2)) {
                    if (!vis.count(nxt)) {
                        vis.insert(nxt);
                        q.push(nxt);
                    }
                }
            }
            ++ans;
        }
    }

    vector<string> next(string& s, string& s2) {
        int i = 0, n = s.size();
        for (; s[i] == s2[i]; ++i) {}
        vector<string> res;
        for (int j = i + 1; j < n; ++j) {
            if (s[j] == s2[i] && s[j] != s2[j]) {
                swap(s[i], s[j]);
                res.push_back(s);
                swap(s[i], s[j]);
            }
        }
        return res;
    }
};