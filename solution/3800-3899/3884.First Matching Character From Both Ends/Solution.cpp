class Solution {
public:
    int firstMatchingIndex(string s) {
        int n = s.size();
        for (int i = 0; i < n / 2 + 1; ++i) {
            if (s[i] == s[n - i - 1]) {
                return i;
            }
        }
        return -1;
    }
};
