class Solution {
public:
    string getEncryptedString(string s, int k) {
        int n = s.length();
        string cs(n, ' ');
        for (int i = 0; i < n; ++i) {
            cs[i] = s[(i + k) % n];
        }
        return cs;
    }
};