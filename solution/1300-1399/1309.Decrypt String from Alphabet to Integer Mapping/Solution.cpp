class Solution {
public:
    string freqAlphabets(string s) {
        string ans = "";
        int i = 0, n = s.size();
        while (i < n) {
            if (i + 2 < n && s[i + 2] == '#') {
                ans += char(stoi(s.substr(i, 2)) + 'a' - 1);
                i += 3;
            } else {
                ans += char(s[i] - '0' + 'a' - 1);
                i += 1;
            }
        }
        return ans;
    }
};
