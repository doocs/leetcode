class Solution {
public:
    string abbreviateProduct(int left, int right) {
        int cnt2 = 0, cnt5 = 0;
        for (int i = left; i <= right; ++i) {
            int x = i;
            for (; x % 2 == 0; x /= 2) {
                ++cnt2;
            }
            for (; x % 5 == 0; x /= 5) {
                ++cnt5;
            }
        }
        int c = min(cnt2, cnt5);
        cnt2 = cnt5 = c;
        long long suf = 1;
        long double pre = 1;
        bool gt = false;
        for (int i = left; i <= right; ++i) {
            for (suf *= i; cnt2 && suf % 2 == 0; suf /= 2) {
                --cnt2;
            }
            for (; cnt5 && suf % 5 == 0; suf /= 5) {
                --cnt5;
            }
            if (suf >= 1e10) {
                gt = true;
                suf %= (long long) 1e10;
            }
            for (pre *= i; pre > 1e5; pre /= 10) {
            }
        }
        if (gt) {
            char buf[10];
            snprintf(buf, sizeof(buf), "%0*lld", 5, suf % (int) 1e5);
            return to_string((int) pre) + "..." + string(buf) + "e" + to_string(c);
        }
        return to_string(suf) + "e" + to_string(c);
    }
};