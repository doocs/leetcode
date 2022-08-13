class Solution {
public:
    long long maximumSubsequenceCount(string text, string pattern) {
        long long ans = 0;
        char a = pattern[0], b = pattern[1];
        vector<int> cnt(26);
        for (char& c : text) {
            if (c == b) ans += cnt[a - 'a'];
            cnt[c - 'a']++;
        }
        ans += max(cnt[a - 'a'], cnt[b - 'a']);
        return ans;
    }
};