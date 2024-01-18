using pis = pair<int, string>;

class Solution {
public:
    int kSimilarity(string s1, string s2) {
        priority_queue<pis, vector<pis>, greater<pis>> q;
        q.push({f(s1, s2), s1});
        unordered_map<string, int> dist;
        dist[s1] = 0;
        while (1) {
            auto [_, s] = q.top();
            q.pop();
            if (s == s2) {
                return dist[s];
            }
            for (auto& nxt : next(s, s2)) {
                if (!dist.count(nxt) || dist[nxt] > dist[s] + 1) {
                    dist[nxt] = dist[s] + 1;
                    q.push({dist[nxt] + f(nxt, s2), nxt});
                }
            }
        }
    }

    int f(string& s, string& s2) {
        int cnt = 0;
        for (int i = 0; i < s.size(); ++i) {
            cnt += s[i] != s2[i];
        }
        return (cnt + 1) >> 1;
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