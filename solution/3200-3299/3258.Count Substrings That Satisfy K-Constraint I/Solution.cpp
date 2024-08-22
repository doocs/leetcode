class Solution {
public:
    int countKConstraintSubstrings(string s, int k) {
        int cnt0 = 0, cnt1 = 0;
        int ans = 0, l = 0;
        for (int r = 0; r < s.length(); ++r) {
            int x = s[r] - '0';
            cnt0 += x ^ 1;
            cnt1 += x;
            while (cnt0 > k && cnt1 > k) {
                int y = s[l++] - '0';
                cnt0 -= y ^ 1;
                cnt1 -= y;
            }
            ans += r - l + 1;
        }
        return ans;
    }
};
