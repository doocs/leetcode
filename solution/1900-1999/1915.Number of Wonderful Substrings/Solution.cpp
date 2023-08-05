class Solution {
public:
    long long wonderfulSubstrings(string word) {
        int cnt[1024] = {1};
        long long ans = 0;
        int st = 0;
        for (char c : word) {
            st ^= 1 << (c - 'a');
            ans += cnt[st];
            for (int i = 0; i < 10; ++i) {
                ans += cnt[st ^ (1 << i)];
            }
            ++cnt[st];
        }
        return ans;
    }
};