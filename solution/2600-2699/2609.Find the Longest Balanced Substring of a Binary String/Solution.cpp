class Solution {
public:
    int findTheLongestBalancedSubstring(string s) {
        int n = s.size();
        int ans = 0;
        auto check = [&](int i, int j) -> bool {
            int cnt = 0;
            for (int k = i; k <= j; ++k) {
                if (s[k] == '1') {
                    ++cnt;
                } else if (cnt) {
                    return false;
                }
            }
            return cnt * 2 == j - i + 1;
        };
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (check(i, j)) {
                    ans = max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }
};