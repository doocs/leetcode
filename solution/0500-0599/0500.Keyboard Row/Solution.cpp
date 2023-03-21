class Solution {
public:
    vector<string> findWords(vector<string>& words) {
        string s = "12210111011122000010020202";
        vector<string> ans;
        for (auto& w : words) {
            char x = s[tolower(w[0]) - 'a'];
            bool ok = true;
            for (char& c : w) {
                if (s[tolower(c) - 'a'] != x) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                ans.emplace_back(w);
            }
        }
        return ans;
    }
};