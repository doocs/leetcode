class Solution {
public:
    string licenseKeyFormatting(string s, int k) {
        string ss = "";
        for (char c : s) {
            if (c == '-') continue;
            if ('a' <= c && c <= 'z') c += 'A' - 'a';
            ss += c;
        }
        int cnt = ss.size() % k;
        if (cnt == 0) cnt = k;
        int t = 0;
        string res = "";
        for (int i = 0; i < ss.size(); ++i) {
            res += ss[i];
            ++t;
            if (t == cnt) {
                t = 0;
                cnt = k;
                if (i != ss.size() - 1) res += '-';
            }
        }
        return res;
    }
};