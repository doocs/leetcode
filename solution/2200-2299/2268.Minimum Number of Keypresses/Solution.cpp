class Solution {
public:
    int minimumKeypresses(string s) {
        int cnt[26]{};
        for (char& c : s) {
            ++cnt[c - 'a'];
        }
        sort(begin(cnt), end(cnt), greater<int>());
        int ans = 0, k = 1;
        for (int i = 1; i <= 26; ++i) {
            ans += k * cnt[i - 1];
            if (i % 9 == 0) {
                ++k;
            }
        }
        return ans;
    }
};