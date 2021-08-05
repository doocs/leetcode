class Solution {
public:
    int countConsistentStrings(string allowed, vector<string>& words) {
        vector<bool> chars(26, false);
        for (char c : allowed) {
            chars[c - 'a'] = true;
        }
        int res = 0;
        for (string word : words) {
            bool find = true;
            for (char c : word) {
                if (!chars[c - 'a']) {
                    find = false;
                    break;
                }
            }
            if (find) ++res;
        }
        return res;
    }
};