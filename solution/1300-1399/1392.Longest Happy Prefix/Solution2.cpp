class Solution {
public:
    string longestPrefix(string s) {
        s.push_back('#');
        int n = s.size();
        int next[n];
        next[0] = -1;
        next[1] = 0;
        for (int i = 2, j = 0; i < n;) {
            if (s[i - 1] == s[j]) {
                next[i++] = ++j;
            } else if (j > 0) {
                j = next[j];
            } else {
                next[i++] = 0;
            }
        }
        return s.substr(0, next[n - 1]);
    }
};
