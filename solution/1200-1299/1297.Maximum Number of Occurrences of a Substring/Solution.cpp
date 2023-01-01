class Solution {
public:
    int maxFreq(string s, int maxLetters, int minSize, int maxSize) {
        int ans = 0;
        unordered_map<string, int> cnt;
        for (int i = 0; i < s.size() - minSize + 1; ++i) {
            string t = s.substr(i, minSize);
            unordered_set<char> ss(t.begin(), t.end());
            if (ss.size() <= maxLetters) {
                ans = max(ans, ++cnt[t]);
            }
        }
        return ans;
    }
};