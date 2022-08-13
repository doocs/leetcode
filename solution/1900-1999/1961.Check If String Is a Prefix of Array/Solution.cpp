class Solution {
public:
    bool isPrefixString(string s, vector<string>& words) {
        string t = "";
        for (string& w : words) {
            t += w;
            if (t.size() == s.size()) return t == s;
        }
        return false;
    }
};