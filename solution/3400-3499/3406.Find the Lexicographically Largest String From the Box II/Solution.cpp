class Solution {
public:
    string answerString(string word, int numFriends) {
        if (numFriends == 1) {
            return word;
        }
        string s = lastSubstring(word);
        return s.substr(0, min(s.length(), word.length() - numFriends + 1));
    }

    string lastSubstring(string& s) {
        int n = s.size();
        int i = 0, j = 1, k = 0;
        while (j + k < n) {
            if (s[i + k] == s[j + k]) {
                ++k;
            } else if (s[i + k] < s[j + k]) {
                i += k + 1;
                k = 0;
                if (i >= j) {
                    j = i + 1;
                }
            } else {
                j += k + 1;
                k = 0;
            }
        }
        return s.substr(i);
    }
};