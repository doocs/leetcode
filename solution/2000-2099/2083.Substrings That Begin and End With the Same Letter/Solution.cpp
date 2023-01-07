class Solution {
public:
    long long numberOfSubstrings(string s) {
        int cnt[26]{};
        long long ans = 0;
        for (char& c : s) {
            ans += ++cnt[c - 'a'];
        }
        return ans;
    }
};