class Solution {
public:
    vector<int> validSequence(string word1, string word2) {
        int m = word1.size(), n = word2.size();
        vector<int> suf(n + 1, -1);
        suf[n] = m;
        for (int i = m - 1, j = n - 1; i >= 0; i--) {
            if (word1[i] == word2[j]) suf[j--] = i;
            if (j < 0) break;
        }
        vector<int> res(n);
        bool changed = false;
        for (int i = 0, j = 0; i < m; i++) {
            if (word1[i] == word2[j]) res[j++] = i;
            else if (!changed && i < suf[j + 1]) {
                changed = true;
                res[j++] = i;
            }
            if (j == n) return res;
        }
        return {};
    }
};
