class Solution {
public:
    string reversePrefix(string s, int k) {
        string t = s.substr(0, k);
        reverse(t.begin(), t.end());
        return t + s.substr(k);
    }
};
