class Solution {
public:
    int minimumTime(string s) {
        int n = s.size();
        vector<int> pre(n + 1);
        vector<int> suf(n + 1);
        for (int i = 0; i < n; ++i) pre[i + 1] = s[i] == '0' ? pre[i] : min(pre[i] + 2, i + 1);
        for (int i = n - 1; ~i; --i) suf[i] = s[i] == '0' ? suf[i + 1] : min(suf[i + 1] + 2, n - i);
        int ans = INT_MAX;
        for (int i = 1; i <= n; ++i) ans = min(ans, pre[i] + suf[i]);
        return ans;
    }
};