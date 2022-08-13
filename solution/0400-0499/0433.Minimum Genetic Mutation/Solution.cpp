class Solution {
public:
    int minMutation(string start, string end, vector<string>& bank) {
        unordered_set<string> s;
        for (auto& b : bank) s.insert(b);
        unordered_map<char, string> mp;
        mp['A'] = "TCG";
        mp['T'] = "ACG";
        mp['C'] = "ATG";
        mp['G'] = "ATC";
        queue<pair<string, int>> q;
        q.push({start, 0});
        while (!q.empty()) {
            auto p = q.front();
            q.pop();
            string t = p.first;
            int step = p.second;
            if (t == end) return step;
            for (int i = 0; i < t.size(); ++i) {
                for (char c : mp[t[i]]) {
                    string next = t.substr(0, i) + c + t.substr(i + 1, t.size() - i - 1);
                    if (s.count(next)) {
                        q.push({next, step + 1});
                        s.erase(next);
                    }
                }
            }
        }
        return -1;
    }
};