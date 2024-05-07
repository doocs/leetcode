class Solution {
public:
    int findClosest(vector<string>& words, string word1, string word2) {
        const int inf = 1 << 29;
        int i = inf, j = -inf;
        int ans = inf;
        for (int k = 0; k < words.size(); ++k) {
            if (words[k] == word1) {
                i = k;
            } else if (words[k] == word2) {
                j = k;
            }
            ans = min(ans, abs(i - j));
        }
        return ans;
    }
};