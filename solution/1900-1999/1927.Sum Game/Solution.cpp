class Solution {
public:
    bool sumGame(string num) {
        int n = num.size();
        int cnt1 = 0, cnt2 = 0;
        int s1 = 0, s2 = 0;
        for (int i = 0; i < n / 2; ++i) {
            if (num[i] == '?') {
                cnt1++;
            } else {
                s1 += num[i] - '0';
            }
        }
        for (int i = n / 2; i < n; ++i) {
            if (num[i] == '?') {
                cnt2++;
            } else {
                s2 += num[i] - '0';
            }
        }
        return (cnt1 + cnt2) % 2 == 1 || (s1 - s2) != 9 * (cnt2 - cnt1) / 2;
    }
};