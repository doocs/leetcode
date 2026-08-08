class Solution {
public:
    vector<int> validSequence(string word1, string word2) {
        int m = word1.size(), n = word2.size();

        vector<int> suf(m + 1);
        suf[m] = n;

        int j = n - 1;
        for (int i = m - 1; i >= 0; i--) {
            if (j >= 0 && word1[i] == word2[j]) {
                j--;
            }
            suf[i] = j + 1;
        }

        vector<int> ans;
        bool changed = false;
        j = 0;

        for (int i = 0; i < m; i++) {
            char c = word1[i];
            if (c == word2[j] || (!changed && suf[i + 1] <= j + 1)) {
                if (c != word2[j]) {
                    changed = true;
                }
                ans.push_back(i);
                j++;

                if (j == n) {
                    return ans;
                }
            }
        }

        return {};
    }
};