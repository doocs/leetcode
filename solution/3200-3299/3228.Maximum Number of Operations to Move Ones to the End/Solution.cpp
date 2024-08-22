class Solution {
public:
    int maxOperations(string s) {
        int ans = 0, cnt = 0;
        int n = s.size();
        for (int i = 0; i < n; ++i) {
            if (s[i] == '1') {
                ++cnt;
            } else if (i && s[i - 1] == '1') {
                ans += cnt;
            }
        }
        return ans;
    }
};