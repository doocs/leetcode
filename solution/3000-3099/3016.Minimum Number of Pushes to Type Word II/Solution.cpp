class Solution {
public:
    int minimumPushes(string word) {
        vector<int> cnt(26);
        for (char& c : word) {
            ++cnt[c - 'a'];
        }
        sort(cnt.rbegin(), cnt.rend());
        int ans = 0;
        for (int i = 0; i < 26; ++i) {
            ans += (i / 8 + 1) * cnt[i];
        }
        return ans;
    }
};