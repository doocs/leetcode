class Solution {
public:
    string discountPrices(string sentence, int discount) {
        istringstream is(sentence);
        string w;
        string ans;
        auto check = [](string s) {
            if (s[0] != '$' || s.size() == 1) {
                return false;
            }
            for (int i = 1; i < s.size(); ++i) {
                if (!isdigit(s[i])) {
                    return false;
                }
            }
            return true;
        };
        while (is >> w) {
            if (check(w)) {
                long long v = stoll(w.substr(1)) * (100 - discount);
                char t[20];
                sprintf(t, "$%lld.%02lld", v / 100, v % 100);
                ans += t;
            } else {
                ans += w;
            }
            ans += ' ';
        }
        ans.pop_back();
        return ans;
    }
};