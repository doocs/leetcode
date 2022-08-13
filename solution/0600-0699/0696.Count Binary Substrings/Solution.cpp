class Solution {
public:
    int countBinarySubstrings(string s) {
        int i = 0, n = s.size();
        vector<int> t;
        while (i < n) {
            int cnt = 1;
            while (i + 1 < n && s[i + 1] == s[i]) {
                ++cnt;
                ++i;
            }
            t.push_back(cnt);
            ++i;
        }
        int ans = 0;
        for (i = 1; i < t.size(); ++i) ans += min(t[i - 1], t[i]);
        return ans;
    }
};