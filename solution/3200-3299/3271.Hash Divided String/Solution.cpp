class Solution {
public:
    string stringHash(string s, int k) {
        string ans;
        int n = s.length();
        for (int i = 0; i < n; i += k) {
            int t = 0;
            for (int j = i; j < i + k; ++j) {
                t += s[j] - 'a';
            }
            int hashedChar = t % 26;
            ans += ('a' + hashedChar);
        }
        return ans;
    }
};
