class Solution {
public:
    int minDeletion(string s, int k) {
        vector<int> cnt(26);
        for (char c : s) {
            ++cnt[c - 'a'];
        }
        ranges::sort(cnt);
        int ans = 0;
        for (int i = 0; i + k < 26; ++i) {
            ans += cnt[i];
        }
        return ans;
    }
};