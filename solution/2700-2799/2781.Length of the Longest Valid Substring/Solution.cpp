class Solution {
public:
    int longestValidSubstring(string word, vector<string>& forbidden) {
        unordered_set<string> s(forbidden.begin(), forbidden.end());
        int ans = 0, n = word.size();
        for (int i = 0, j = 0; j < n; ++j) {
            for (int k = j; k > max(j - 10, i - 1); --k) {
                if (s.count(word.substr(k, j - k + 1))) {
                    i = k + 1;
                    break;
                }
            }
            ans = max(ans, j - i + 1);
        }
        return ans;
    }
};