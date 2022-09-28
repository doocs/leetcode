class Solution {
public:
    vector<string> subdomainVisits(vector<string>& cpdomains) {
        unordered_map<string, int> cnt;
        for (auto& s : cpdomains) {
            int i = s.find(' ');
            int v = stoi(s.substr(0, i));
            for (; i < s.size(); ++i) {
                if (s[i] == ' ' || s[i] == '.') {
                    cnt[s.substr(i + 1)] += v;
                }
            }
        }
        vector<string> ans;
        for (auto& [s, v] : cnt) {
            ans.push_back(to_string(v) + " " + s);
        }
        return ans;
    }
};