class Solution {
public:
    int minOperations(string s) {
        int cnt = 0, n = s.size();
        for (int i = 0; i < n; ++i) cnt += s[i] != "01"[i & 1];
        return min(cnt, n - cnt);
    }
};