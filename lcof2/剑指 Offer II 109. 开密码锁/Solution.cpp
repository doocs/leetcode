class Solution {
public:
    int openLock(vector<string>& deadends, string target) {
        unordered_set<string> s(deadends.begin(), deadends.end());
        if (s.count(target) || s.count("0000")) return -1;
        if (target == "0000") return 0;
        unordered_set<string> visited;
        queue<string> q;
        q.push("0000");
        int step = 0;
        while (!q.empty()) {
            ++step;
            for (int i = 0, n = q.size(); i < n; ++i) {
                string status = q.front();
                q.pop();
                for (auto t : get(status)) {
                    if (visited.count(t) || s.count(t)) continue;
                    if (t == target) return step;
                    q.push(t);
                    visited.insert(t);
                }
            }
        }
        return -1;
    }

    char prev(char c) {
        return c == '0' ? '9' : (char) (c - 1);
    }

    char next(char c) {
        return c == '9' ? '0' : (char) (c + 1);
    }

    vector<string> get(string& t) {
        vector<string> res;
        for (int i = 0; i < 4; ++i) {
            char c = t[i];
            t[i] = prev(c);
            res.push_back(t);
            t[i] = next(c);
            res.push_back(t);
            t[i] = c;
        }
        return res;
    }
};