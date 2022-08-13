class Solution {
public:
    int findClosest(vector<string>& words, string word1, string word2) {
        int i = 1e5, j = -1e5, ans = 1e5;
        for (int k = 0; k < words.size(); ++k) {
            string word = words[k];
            if (word == word1)
                i = k;
            else if (word == word2)
                j = k;
            ans = min(ans, abs(i - j));
        }
        return ans;
    }
};