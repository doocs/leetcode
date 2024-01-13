class Solution {
public:
    bool arrayStringsAreEqual(vector<string>& word1, vector<string>& word2) {
        int i = 0, j = 0, x = 0, y = 0;
        while (i < word1.size() && j < word2.size()) {
            if (word1[i][x++] != word2[j][y++]) return false;
            if (x == word1[i].size()) x = 0, i++;
            if (y == word2[j].size()) y = 0, j++;
        }
        return i == word1.size() && j == word2.size();
    }
};