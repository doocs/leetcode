class Solution {
public:
    bool canReach(string s, int minJump, int maxJump) {
        int n = s.size();
        vector<int> pre(n + 1);
        pre[1] = 1;
        vector<bool> f(n);
        f[0] = true;
        for (int i = 1; i < n; ++i) {
            if (s[i] == '0') {
                int l = max(0, i - maxJump);
                int r = i - minJump;
                f[i] = l <= r && pre[r + 1] - pre[l];
            }
            pre[i + 1] = pre[i] + f[i];
        }
        return f[n - 1];
    }
};