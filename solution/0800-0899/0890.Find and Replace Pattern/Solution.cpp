class Solution {
public:
    vector<string> findAndReplacePattern(vector<string>& words, string pattern) {
        vector<string> ans;
        auto match = [](string& s, string& t) {
            int m1[128] = {0};
            int m2[128] = {0};
            for (int i = 0; i < s.size(); ++i) {
                if (m1[s[i]] != m2[t[i]]) return 0;
                m1[s[i]] = i + 1;
                m2[t[i]] = i + 1;
            }
            return 1;
        };
        for (auto& word : words)
            if (match(word, pattern)) ans.emplace_back(word);
        return ans;
    }
};