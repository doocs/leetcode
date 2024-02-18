class Solution {
public:
    int countPrefixSuffixPairs(vector<string>& words) {
        int ans = 0;
        int n = words.size();
        for (int i = 0; i < n; ++i) {
            string s = words[i];
            for (int j = i + 1; j < n; ++j) {
                string t = words[j];
                if (t.find(s) == 0 && t.rfind(s) == t.length() - s.length()) {
                    ++ans;
                }
            }
        }
        return ans;
    }
};