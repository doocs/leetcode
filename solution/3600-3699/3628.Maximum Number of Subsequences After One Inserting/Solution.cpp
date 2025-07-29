class Solution {
public:
    long long numOfSubsequences(string s) {
        auto calc = [&](string t) {
            long long cnt = 0, a = 0;
            for (char c : s) {
                if (c == t[1]) {
                    cnt += a;
                }
                a += (c == t[0]);
            }
            return cnt;
        };

        long long l = 0, r = count(s.begin(), s.end(), 'T');
        long long ans = 0, mx = 0;
        for (char c : s) {
            r -= (c == 'T');
            if (c == 'C') {
                ans += l * r;
            }
            l += (c == 'L');
            mx = max(mx, l * r);
        }
        mx = max(mx, calc("LC"));
        mx = max(mx, calc("CT"));
        ans += mx;
        return ans;
    }
};
