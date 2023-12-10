class Solution {
public:
    int removeAlmostEqualCharacters(string word) {
        int ans = 0, n = word.size();
        for (int i = 1; i < n; ++i) {
            if (abs(word[i] - word[i - 1]) < 2) {
                ++ans;
                ++i;
            }
        }
        return ans;
    }
};