class Solution {
public:
    int minimumKeypresses(string s) {
        vector<int> cnt(26);
        for (char& c : s) ++cnt[c - 'a'];
        sort(cnt.begin(), cnt.end());
        int ans = 0;
        for (int i = 1, j = 1; i <= 26; ++i) {
            ans += j * cnt[26 - i];
            if (i % 9 == 0) ++j;
        }
        return ans;
    }
};