class Solution {
public:
    int longestDecomposition(string text) {
        int n = text.size();
        if (n < 2) return n;
        for (int i = 1; i <= n >> 1; ++i) {
            if (text.substr(0, i) == text.substr(n - i)) {
                return 2 + longestDecomposition(text.substr(i, n - i - i));
            }
        }
        return 1;
    }
};