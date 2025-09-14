class Solution {
public:
    int canBeTypedWords(string text, string brokenLetters) {
        bool s[26]{};
        for (char c : brokenLetters) {
            s[c - 'a'] = true;
        }
        int ans = 0;
        stringstream ss(text);
        string w;
        while (ss >> w) {
            for (char c : w) {
                if (s[c - 'a']) {
                    --ans;
                    break;
                }
            }
            ++ans;
        }
        return ans;
    }
};
