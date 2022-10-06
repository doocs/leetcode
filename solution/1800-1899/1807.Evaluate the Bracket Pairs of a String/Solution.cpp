class Solution {
public:
    string evaluate(string s, vector<vector<string>>& knowledge) {
        unordered_map<string, string> d;
        for (auto& e : knowledge) {
            d[e[0]] = e[1];
        }
        string ans;
        int i = 0, n = s.size();
        while (i < n) {
            if (s[i] == '(') {
                int j = s.find(")", i + 1);
                auto t = s.substr(i + 1, j - i - 1);
                ans += d.count(t) ? d[t] : "?";
                i = j;
            } else {
                ans += s[i];
            }
            ++i;
        }
        return ans;
    }
};