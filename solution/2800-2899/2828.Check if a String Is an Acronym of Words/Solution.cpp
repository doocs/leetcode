class Solution {
public:
    bool isAcronym(vector<string>& words, string s) {
        string t;
        for (auto& w : words) {
            t += w[0];
        }
        return t == s;
    }
};