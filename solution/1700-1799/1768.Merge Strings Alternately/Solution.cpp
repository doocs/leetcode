class Solution {
public:
    string mergeAlternately(string word1, string word2) {
        int m = word1.size(), n = word2.size();
        string res;
        for (int i = 0; i < m || i < n; ++i) {
            if (i < m) {
                res.push_back(word1[i]);
            }
            if (i < n) {
                res.push_back(word2[i]);
            }
        }
        return res;
    }
};