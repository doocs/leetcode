class Solution {
public:
    string findValidPair(string s) {
        int cnt[10]{};
        for (char c : s) {
            ++cnt[c - '0'];
        }
        for (int i = 1; i < s.size(); ++i) {
            int x = s[i - 1] - '0';
            int y = s[i] - '0';
            if (x != y && cnt[x] == x && cnt[y] == y) {
                return s.substr(i - 1, 2);
            }
        }
        return "";
    }
};
