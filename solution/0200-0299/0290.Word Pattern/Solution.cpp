class Solution {
public:
    bool wordPattern(string pattern, string s) {
        istringstream is(s);
        vector<string> ws;
        while (is >> s) {
            ws.push_back(s);
        }
        if (pattern.size() != ws.size()) {
            return false;
        }
        unordered_map<char, string> d1;
        unordered_map<string, char> d2;
        for (int i = 0; i < ws.size(); ++i) {
            char a = pattern[i];
            string b = ws[i];
            if ((d1.count(a) && d1[a] != b) || (d2.count(b) && d2[b] != a)) {
                return false;
            }
            d1[a] = b;
            d2[b] = a;
        }
        return true;
    }
};