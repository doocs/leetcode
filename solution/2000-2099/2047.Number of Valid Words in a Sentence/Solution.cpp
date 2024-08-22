class Solution {
public:
    int countValidWords(string sentence) {
        auto check = [](const string& s) -> int {
            bool st = false;
            for (int i = 0; i < s.length(); ++i) {
                if (isdigit(s[i])) {
                    return 0;
                }
                if ((s[i] == '!' || s[i] == '.' || s[i] == ',') && i < s.length() - 1) {
                    return 0;
                }
                if (s[i] == '-') {
                    if (st || i == 0 || i == s.length() - 1) {
                        return 0;
                    }
                    if (!isalpha(s[i - 1]) || !isalpha(s[i + 1])) {
                        return 0;
                    }
                    st = true;
                }
            }
            return 1;
        };

        int ans = 0;
        stringstream ss(sentence);
        string s;
        while (ss >> s) {
            ans += check(s);
        }
        return ans;
    }
};
