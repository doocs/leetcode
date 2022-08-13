class Solution {
public:
    vector<string> findWords(vector<string>& words) {
        string s = "12210111011122000010020202";
        vector<string> ans;
        for (auto& word : words) {
            unordered_set<char> t;
            for (char c : word) t.insert(s[tolower(c) - 'a']);
            if (t.size() == 1) ans.push_back(word);
        }
        return ans;
    }
};