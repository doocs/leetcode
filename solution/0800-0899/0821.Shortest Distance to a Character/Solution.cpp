class Solution {
public:
    vector<int> shortestToChar(string s, char c) {
        int n = s.size();
        const int inf = 1 << 30;
        vector<int> ans(n, inf);
        for (int i = 0, pre = -inf; i < n; ++i) {
            if (s[i] == c) {
                pre = i;
            }
            ans[i] = min(ans[i], i - pre);
        }
        for (int i = n - 1, suf = inf; ~i; --i) {
            if (s[i] == c) {
                suf = i;
            }
            ans[i] = min(ans[i], suf - i);
        }
        return ans;
    }
};