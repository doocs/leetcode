class Solution {
public:
    void reverseWords(vector<char>& s) {
        int n = s.size();
        for (int i = 0, j = 0; j < n; ++j) {
            if (s[j] == ' ') {
                reverse(s, i, j - 1);
                i = j + 1;
            } else if (j == n - 1) {
                reverse(s, i, j);
            }
        }
        reverse(s, 0, n - 1);
    }

    void reverse(vector<char>& s, int i, int j) {
        for (; i < j; ++i, --j) {
            swap(s[i], s[j]);
        }
    }
};