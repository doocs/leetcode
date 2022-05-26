class Solution {
public:
    int findClosest(vector<string>& words, string word1, string word2) {
        int idx1 = 1e5, idx2 = -1e5, ans = 1e5;
        for (int i = 0; i < words.size(); ++i)
        {
            string word = words[i];
            if (word == word1) idx1 = i;
            else if (word == word2) idx2 = i;
            ans = min(ans, abs(idx1 - idx2));
        }
        return ans;
    }
};