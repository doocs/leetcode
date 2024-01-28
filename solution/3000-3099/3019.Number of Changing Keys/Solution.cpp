class Solution {
public:
    int countKeyChanges(string s) {
        int n = s.size();
        int c = 0;
        transform(s.begin(), s.end(), s.begin(), ::tolower);
        for (int i = 0; i < n - 1; i++) {
            if (s[i] != s[i + 1]) {
                c++;
            }
        }
        return c;
    }
};
