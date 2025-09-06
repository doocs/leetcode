class Solution {
public:
    vector<string> spellchecker(vector<string>& wordlist, vector<string>& queries) {
        unordered_set<string> s(wordlist.begin(), wordlist.end());
        unordered_map<string, string> low;
        unordered_map<string, string> pat;
        auto f = [](string& w) {
            string res;
            for (char& c : w) {
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    res += '*';
                } else {
                    res += c;
                }
            }
            return res;
        };
        for (const auto& w : wordlist) {
            string t = w;
            transform(t.begin(), t.end(), t.begin(), ::tolower);
            if (!low.contains(t)) {
                low[t] = w;
            }
            t = f(t);
            if (!pat.contains(t)) {
                pat[t] = w;
            }
        }
        vector<string> ans;
        for (auto& q : queries) {
            if (s.contains(q)) {
                ans.emplace_back(q);
                continue;
            }
            transform(q.begin(), q.end(), q.begin(), ::tolower);
            if (low.contains(q)) {
                ans.emplace_back(low[q]);
                continue;
            }
            q = f(q);
            if (pat.contains(q)) {
                ans.emplace_back(pat[q]);
                continue;
            }
            ans.emplace_back("");
        }
        return ans;
    }
};
