class Solution {
public:
    string maskPII(string s) {
        int i = s.find('@');
        if (i != -1) {
            string ans;
            ans += tolower(s[0]);
            ans += "*****";
            for (int j = i - 1; j < s.size(); ++j) {
                ans += tolower(s[j]);
            }
            return ans;
        }
        string t;
        for (char c : s) {
            if (isdigit(c)) {
                t += c;
            }
        }
        int cnt = t.size() - 10;
        string suf = "***-***-" + t.substr(t.size() - 4);
        return cnt == 0 ? suf : "+" + string(cnt, '*') + "-" + suf;
    }
};