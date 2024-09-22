class Solution {
public:
    bool reportSpam(vector<string>& message, vector<string>& bannedWords) {
        unordered_set<string> s(bannedWords.begin(), bannedWords.end());
        int cnt = 0;
        for (const auto& w : message) {
            if (s.contains(w) && ++cnt >= 2) {
                return true;
            }
        }
        return false;
    }
};
