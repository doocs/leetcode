class Solution {
public:
    int canBeTypedWords(string text, string brokenLetters) {
        bool s[26]{};
        for (char& c : brokenLetters) {
            s[c - 'a'] = true;
        }
        int ans = 0;
        for (auto& w : split(text, ' ')) {
            for (char& c : w) {
                if (s[c - 'a']) {
                    --ans;
                    break;
                }
            }
            ++ans;
        }
        return ans;
    }

    vector<string> split(const string& s, char c) {
        vector<string> ans;
        string t;
        for (char d : s) {
            if (d == c) {
                ans.push_back(t);
                t.clear();
            } else {
                t.push_back(d);
            }
        }
        ans.push_back(t);
        return ans;
    }
};