class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int cnt1 = 0, cnt2 = 0;
        int s1 = 0, s2 = 0;
        for (int i = 0; i < n / 2; ++i) {
            if (num.charAt(i) == '?') {
                cnt1++;
            } else {
                s1 += num.charAt(i) - '0';
            }
        }
        for (int i = n / 2; i < n; ++i) {
            if (num.charAt(i) == '?') {
                cnt2++;
            } else {
                s2 += num.charAt(i) - '0';
            }
        }
        return (cnt1 + cnt2) % 2 == 1 || s1 - s2 != 9 * (cnt2 - cnt1) / 2;
    }
}