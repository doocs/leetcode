class Solution {
public:
    int kSimilarity(string s1, string s2) {
        queue<string> q {{s1}};
        unordered_set<string> vis {{s1}};
        int ans = 0;
        while (!q.empty()) {
            for (int i = q.size(); i; --i) {
                s1 = q.front();
                q.pop();
                if (s1 == s2) return ans;
                for (string nxt : next(s1, s2)) {
                    if (!vis.count(nxt)) {
                        vis.insert(nxt);
                        q.push(nxt);
                    }
                }
            }
            ++ans;
        }
        return -1;
    }

    vector<string> next(string& s, string& s2) {
        int i = 0, n = s.size();
        for (; i < n && s[i] == s2[i]; ++i)
            ;
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