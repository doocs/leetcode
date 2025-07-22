class Solution {
public:
    long long numberOfWays(string s) {
        int n = s.size();
        int l[2]{};
        int r[2]{};
        r[0] = ranges::count(s, '0');
        r[1] = n - r[0];
        long long ans = 0;
        for (int i = 0; i < n; ++i) {
            int x = s[i] - '0';
            r[x]--;
            ans += 1LL * l[x ^ 1] * r[x ^ 1];
            l[x]++;
        }
        return ans;
    }
};
