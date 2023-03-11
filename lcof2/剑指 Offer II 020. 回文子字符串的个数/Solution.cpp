class Solution {
public:
    int countSubstrings(string s) {
        int ans = 0;
        int n = s.size();
        auto f = [&](int i, int j) -> int {
            int cnt = 0;
            for (; i >= 0 && j < n && s[i] == s[j]; --i, ++j) {
                ++cnt;
            }
            return cnt;
        };
        for (int i = 0; i < n; ++i) {
            ans += f(i, i) + f(i, i + 1);
        }
        return ans;
    }
};