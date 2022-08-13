class Solution {
public:
    vector<string> findAndReplacePattern(vector<string>& words, string pattern) {
        vector<string> ans;
        for (auto& word : words)
            if (match(word, pattern))
                ans.push_back(word);
        return ans;
    }

    bool match(string s, string t) {
        vector<int> m1(128);
        vector<int> m2(128);
        for (int i = 0; i < s.size(); ++i) {
            if (m1[s[i]] != m2[t[i]]) return 0;
            m1[s[i]] = i + 1;
            m2[t[i]] = i + 1;
        }
        return 1;
    }
};