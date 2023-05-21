class Solution {
public:
    string oddString(vector<string>& words) {
        unordered_map<string, vector<string>> d;
        for (auto& s : words) {
            int m = s.size();
            string t(m - 1, 0);
            for (int i = 0; i < m - 1; ++i) {
                t[i] = s[i + 1] - s[i];
            }
            d[t].push_back(s);
        }
        for (auto& [_, ss] : d) {
            if (ss.size() == 1) {
                return ss[0];
            }
        }
        return "";
    }
};