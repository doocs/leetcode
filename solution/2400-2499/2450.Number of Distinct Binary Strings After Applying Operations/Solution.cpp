class Solution {
public:
    const int mod = 1e9 + 7;

    int countDistinctStrings(string s, int k) {
        int ans = 1;
        for (int i = 0; i < s.size() - k + 1; ++i) {
            ans = (ans * 2) % mod;
        }
        return ans;
    }
};