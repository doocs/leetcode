class Solution {
public:
    string smallestGoodBase(string n) {
        long long num = stoll(n);
        for (int len = 63; len >= 2; --len) {
            long long radix = getRadix(len, num);
            if (radix != -1) {
                return to_string(radix);
            }
        }
        return to_string(num - 1);
    }

    long long getRadix(int len, long long num) {
        long long l = 2, r = num - 1;
        while (l < r) {
            long long mid = (l + r) >> 1;
            if (calc(mid, len) >= num) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return calc(r, len) == num ? r : -1;
    }

    long long calc(long long radix, int len) {
        long long p = 1, sum = 0;
        for (int i = 0; i < len; ++i) {
            if (LLONG_MAX - sum < p) {
                return LLONG_MAX;
            }
            sum += p;
            if (LLONG_MAX / p < radix) {
                p = LLONG_MAX;
            } else {
                p *= radix;
            }
        }
        return sum;
    }
};
