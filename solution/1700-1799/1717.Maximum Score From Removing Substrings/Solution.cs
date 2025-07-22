public class Solution {
    public int MaximumGain(string s, int x, int y) {
        char a = 'a', b = 'b';
        if (x < y) {
            (x, y) = (y, x);
            (a, b) = (b, a);
        }

        int ans = 0, cnt1 = 0, cnt2 = 0;
        foreach (char c in s) {
            if (c == a) {
                cnt1++;
            } else if (c == b) {
                if (cnt1 > 0) {
                    ans += x;
                    cnt1--;
                } else {
                    cnt2++;
                }
            } else {
                ans += Math.Min(cnt1, cnt2) * y;
                cnt1 = 0;
                cnt2 = 0;
            }
        }

        ans += Math.Min(cnt1, cnt2) * y;
        return ans;
    }
}
