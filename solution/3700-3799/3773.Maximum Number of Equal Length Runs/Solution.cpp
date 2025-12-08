class Solution {
public:
    int maxSameLengthRuns(string s) {
        unordered_map<int, int> cnt;
        int ans = 0;
        int n = s.size();
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && s[j] == s[i]) {
                ++j;
            }
            int m = j - i;
            ans = max(ans, ++cnt[m]);
            i = j;
        }
        return ans;
    }
};
