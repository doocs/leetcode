class Solution {
public:
    vector<string> letterCasePermutation(string s) {
        int n = 0;
        for (char c : s)
            if (isalpha(c)) ++n;
        vector<string> ans;
        for (int i = 0; i < 1 << n; ++i) {
            int j = 0;
            string t;
            for (char c : s) {
                if (isalpha(c)) {
                    c = (i >> j & 1) ? tolower(c) : toupper(c);
                    ++j;
                }
                t += c;
            }
            ans.emplace_back(t);
        }
        return ans;
    }
};